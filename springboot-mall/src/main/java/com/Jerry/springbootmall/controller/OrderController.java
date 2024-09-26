package com.Jerry.springbootmall.controller;


import com.Jerry.springbootmall.dto.CreateOrderRequest;
import com.Jerry.springbootmall.dto.OrderQueryParam;
import com.Jerry.springbootmall.model.Order;
import com.Jerry.springbootmall.service.OrderService;
import com.Jerry.springbootmall.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order/{userId}/orders")
    public ResponseEntity<Order> createOrder(@PathVariable Integer userId,
                                             @RequestBody @Valid CreateOrderRequest createOrderRequest) {
        Integer orderId = orderService.createOrder(userId, createOrderRequest);
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);

    }

    @GetMapping("/user/{userId}/orders")
    public ResponseEntity<Page<Order>> getOrders(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "10") @Max(20) @Min(10) Integer limit,
            @RequestParam( defaultValue = "0") @Min(0)Integer offset
            ) {
        OrderQueryParam orderQueryParam = new OrderQueryParam();
        orderQueryParam.setUserId(userId);
        orderQueryParam.setLimit(limit);
        orderQueryParam.setOffset(offset);

        List<Order> orders = orderService.getOrders(orderQueryParam);
        Integer totalOrder = orderService.getCountOrder(orderQueryParam);
        Page<Order> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setResults(orders);

        return ResponseEntity.status(HttpStatus.OK).body(page);


    }
}