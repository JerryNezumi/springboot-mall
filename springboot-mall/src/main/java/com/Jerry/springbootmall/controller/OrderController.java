package com.Jerry.springbootmall.controller;


import com.Jerry.springbootmall.dto.CreateOrderRequest;
import com.Jerry.springbootmall.model.Order;
import com.Jerry.springbootmall.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                         @RequestBody @Valid CreateOrderRequest createOrderRequest) {
        Integer id = orderService.createOrder(userId,createOrderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);

    }
}