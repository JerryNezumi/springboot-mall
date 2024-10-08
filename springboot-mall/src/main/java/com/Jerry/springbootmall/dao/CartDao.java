package com.Jerry.springbootmall.dao;

import com.Jerry.springbootmall.dto.CartItemRequest;
import com.Jerry.springbootmall.model.CartItem;

import java.util.List;

public interface CartDao {
    Integer createCart(Integer userId);
    Integer createCartItem(Integer cartId, CartItemRequest cartItemRequest);
    void deleteCartItem(Integer cartItemId);
    List<CartItem> getCartItems(Integer userId);
    void updateCartItem(Integer cartItemId, CartItemRequest cartItemRequest);
    CartItem getCartItemById(Integer cartItemId);

}
