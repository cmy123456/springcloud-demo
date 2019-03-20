package com.baizhi.service;

import com.baizhi.dto.ProductCategoryDto;
import com.baizhi.dto.ProductDto;
import com.baizhi.dto.ResultDto;
import com.baizhi.entity.Product;
import com.baizhi.entity.ProductCategory;
import com.baizhi.mapper.ProductCategoryMapper;
import com.baizhi.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public List<Product> findAllProduct() {
        return productMapper.selectAll();
    }

    @Override
    public ResultDto findProductDto() {
        ResultDto resultDto = new ResultDto();

        resultDto.setCode(0);
        resultDto.setMsg("成功");
        List<ProductCategory> productCategories = productCategoryMapper.selectAll();
        List<ProductCategoryDto> data = resultDto.getData();

        for (ProductCategory productCategory : productCategories) {
            ProductCategoryDto productCategoryDto = new ProductCategoryDto();
            productCategoryDto.setName(productCategory.getCategoryName());
            productCategoryDto.setType(productCategory.getCategoryType());

            //product赋值
            Product product = new Product();
            product.setCategoryType(productCategory.getCategoryType());
            List<Product> products = productMapper.select(product);
            List<ProductDto> foods = productCategoryDto.getFoods();
            for (Product p : products) {
                ProductDto productDto = new ProductDto();
                productDto.setId(p.getProductId());
                productDto.setName(p.getProductName());
                productDto.setPrice(p.getProductPrice());
                productDto.setDescription(p.getProductDescription());
                productDto.setIcon(p.getProductIcon());
                foods.add(productDto);
            }
            data.add(productCategoryDto);
        }
        return resultDto;
    }

    @Override
    public Product getOneProduct(String id) {
        Product product = productMapper.selectByPrimaryKey(id);
        return product;

    }

}
