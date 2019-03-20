package com.baizhi.feign;

import com.baizhi.entity.Product;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("PRODUCT")
public interface ProductFeign  {
    @RequestMapping("/pro/getOneProduct")
    public Product getOneProduct(@RequestParam("id") String id);
}
