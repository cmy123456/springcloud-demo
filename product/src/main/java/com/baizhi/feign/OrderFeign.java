package com.baizhi.feign;

import com.baizhi.dto.OrderDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("ORDER")
public interface OrderFeign  {
    @RequestMapping(value = "/order/createOrder",method = RequestMethod.POST)
    public String createOrder(OrderDto orderDto);
}
