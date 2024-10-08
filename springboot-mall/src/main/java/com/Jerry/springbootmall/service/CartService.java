package com.Jerry.springbootmall.service;

import com.Jerry.springbootmall.dto.CartItemRequest;
import com.Jerry.springbootmall.model.CartItem;

import java.util.List;

public interface CartService {
    Integer addCart(Integer userId);
    Integer createCartItem(Integer cartId, CartItemRequest cartItemRequest);
    void deleteCartItem(Integer cartItemId);
    List<CartItem> getCartItems(Integer userId);
    CartItem updateCartItem(Integer cartItemId, CartItemRequest cartItemRequest);
}
