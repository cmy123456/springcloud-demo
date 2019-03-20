package com.baizhi.service;

import com.baizhi.dto.ResultDto;
import com.baizhi.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAllProduct();
    public ResultDto findProductDto();
    public Product getOneProduct(String id);
}
