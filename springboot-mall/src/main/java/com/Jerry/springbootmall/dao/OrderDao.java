package com.Jerry.springbootmall.dao;

import com.Jerry.springbootmall.dto.CreateOrderRequest;
import com.Jerry.springbootmall.dto.OrderQueryParam;
import com.Jerry.springbootmall.model.Order;
import com.Jerry.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {
    Integer createOrder(Integer userId,Integer totalAmount);
    void createOrderItems(Integer orderId, List<OrderItem> orderItems);
    Order getOrderById(Integer orderId);
    List<OrderItem> getOrderItemsByOrderId(Integer orderId);
    List<Order> getOrders(OrderQueryParam orderQueryParam);
    Integer getCountOrder(OrderQueryParam orderQueryParam);
}
