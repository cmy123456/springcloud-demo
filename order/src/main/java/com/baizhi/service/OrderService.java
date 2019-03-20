package com.baizhi.service;

import com.baizhi.dto.OrderDto;
import com.baizhi.dto.OrderResultDto;

public interface OrderService {
    public OrderResultDto createOrder(OrderDto orderDto);


}
