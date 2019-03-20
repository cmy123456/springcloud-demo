package com.baizhi.controller;

import com.baizhi.dto.OrderDto;
import com.baizhi.dto.OrderResultDto;
import com.baizhi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController  {
    
    @Autowired
    private OrderService orderService;

    @RequestMapping("createOrder")
    public OrderResultDto createOrder(OrderDto orderDto){
        OrderResultDto order = orderService.createOrder(orderDto);
        return order;

    }
}
