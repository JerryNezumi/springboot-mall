package com.Jerry.springbootmall.service;

import com.Jerry.springbootmall.dto.CreateOrderRequest;
import com.Jerry.springbootmall.model.Order;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
    Order getOrderById(Integer orderId);
}
