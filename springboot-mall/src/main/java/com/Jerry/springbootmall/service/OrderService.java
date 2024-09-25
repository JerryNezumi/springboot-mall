package com.Jerry.springbootmall.service;

import com.Jerry.springbootmall.dto.CreateOrderRequest;
import com.Jerry.springbootmall.dto.OrderQueryParam;
import com.Jerry.springbootmall.model.Order;

import java.util.List;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
    Order getOrderById(Integer orderId);
    List<Order> getOrders(OrderQueryParam orderQueryParam);
    Integer getCountOrder(OrderQueryParam orderQueryParam);
}
