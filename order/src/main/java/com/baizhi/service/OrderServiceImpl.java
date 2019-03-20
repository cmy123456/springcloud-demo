package com.baizhi.service;

import com.baizhi.dto.OrderDto;
import com.baizhi.dto.OrderItemDto;
import com.baizhi.dto.OrderResultDto;
import com.baizhi.entity.OrderDetail;
import com.baizhi.entity.OrderMaster;
import com.baizhi.entity.Product;
import com.baizhi.feign.ProductFeign;
import com.baizhi.mapper.OrderDetailMapper;
import com.baizhi.mapper.OrderMasterMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductFeign productFeign;
    @Autowired
    private OrderMasterMapper orderMasterMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public OrderResultDto createOrder(OrderDto orderDto) {
        String orderId= UUID.randomUUID().toString().replace("-", "");
        //给OrderMaster赋值入库
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerName(orderDto.getName());
        orderMaster.setBuyerAddress(orderDto.getAddress());
        orderMaster.setBuyerIphone(orderDto.getPhone());
        orderMaster.setBuyerOpenid(orderDto.getOpenId());
        orderMaster.setCreateTime(new Date());
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderStatus(0);
        orderMaster.setPayStatus(0);

        //订单总价
       Double total=0.0;

       //订单项赋值存库
        List<OrderItemDto> items = orderDto.getItems();
        for (OrderItemDto item : items) {
            Product product = (Product) productFeign.getOneProduct(item.getProductId());
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setDetailId(product.getProductId());
            orderDetail.setOrderId(orderId);
            orderDetail.setCreateTime(new Date());
            orderDetail.setProductIcon(product.getProductIcon());
            orderDetail.setProductName(product.getProductName());
            orderDetail.setProductPrice(product.getProductPrice()*item.getProductQuantity());
           //order项入库
            orderDetailMapper.insert(orderDetail);
            total+=orderDetail.getProductPrice();
        }
        orderMaster.setOrderAmount(total);
        //order入库
        orderMasterMapper.insert(orderMaster);

        //最终订单
        OrderResultDto orderResultDto = new OrderResultDto();
        orderResultDto.setCode("200");
        orderResultDto.setMsg("成功");
        Map<String, String> data = orderResultDto.getData();
        data.put("orderId",orderId);
        return orderResultDto;

    }
}
