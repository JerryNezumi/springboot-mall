package com.Jerry.springbootmall.dao;

import com.Jerry.springbootmall.dto.CartItemRequest;

public interface CartDao {
    Integer createCart(Integer userId);
    Integer createCartItem(Integer cartId, CartItemRequest cartItemRequest);
    void deleteCartItem(Integer cartItemId);
}
