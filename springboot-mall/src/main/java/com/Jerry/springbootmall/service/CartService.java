package com.Jerry.springbootmall.service;

import com.Jerry.springbootmall.dto.CartItemRequest;

public interface CartService {
    Integer addCart(Integer userId);
    Integer createCartItem(Integer cartId, CartItemRequest cartItemRequest);
    void deleteCartItem(Integer cartItemId);
}
