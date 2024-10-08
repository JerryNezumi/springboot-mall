package com.Jerry.springbootmall.controller;

import com.Jerry.springbootmall.dto.CartItemRequest;
import com.Jerry.springbootmall.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    //添加商品到購物車
    @PostMapping("/user/{userId}/cart")
    public ResponseEntity<String> addCart(@PathVariable Integer userId,
                                  @RequestBody @Valid CartItemRequest cartItemRequest) {

        int cartId = cartService.addCart(userId);
        Integer cartItemId = cartService.createCartItem(cartId, cartItemRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("商品已成功加入購物車，ID:" +cartItemId );

    }


    //刪除購物車中的商品
    @DeleteMapping("/user/cart/{cartItemId}")
    public ResponseEntity<String> deleteCart(@PathVariable Integer CartItemId) {
        cartService.deleteCartItem(CartItemId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}