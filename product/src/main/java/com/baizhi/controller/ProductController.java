package com.baizhi.controller;

import com.baizhi.dto.ResultDto;
import com.baizhi.entity.Product;
import com.baizhi.service.ProductService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/pro")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("findAllProduct")
    public String findAllProduct(HttpServletRequest request){
        List<Product> allProduct = productService.findAllProduct();
        request.setAttribute("list",allProduct);
        return "showAllProduct";
    }
    @RequestMapping("findProductDto")
    @ResponseBody
    public ResultDto findProductDto(){
        return productService.findProductDto();
    }

    @RequestMapping("getOneProduct")
    public Product getOneProduct(String id){
        Product product = productService.getOneProduct(id);
        return product;
    }
}
