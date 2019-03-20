package com.baizhi.controller;

import com.baizhi.dto.OrderDto;
import com.baizhi.dto.OrderItemDto;
import com.baizhi.feign.OrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.annotation.Order;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private OrderFeign orderFeign;

    @RequestMapping("addCart")
    public String addCart(String id, HttpSession session){
        Map<String,Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
        if (cart==null){
            cart=new HashMap<>();
        }
        if (cart.containsKey(id)){
            Integer count = cart.get(id);
            cart.put(id,count+1);
        }else{
            cart.put(id,1);
        }

        session.setAttribute("cart",cart);
        return "redirect:/cart.jsp";
    }

    @RequestMapping("createOrder")
    @ResponseBody
    public String createOrder(HttpSession session){
        OrderDto orderDto = new OrderDto();
        orderDto.setName("cmy");
        orderDto.setAddress("beijing");
        orderDto.setOpenId("351928722");
        orderDto.setPhone("15713735180");
        List<OrderItemDto> items = orderDto.getItems();
        //给订单向赋值
        Map<String,Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            OrderItemDto orderItemDto = new OrderItemDto();
            orderItemDto.setProductId(entry.getKey());
            orderItemDto.setProductQuantity(entry.getValue());
            items.add(orderItemDto);
        }
        //调用order项目
        String order = orderFeign.createOrder(orderDto);
        return order;
    }
}
