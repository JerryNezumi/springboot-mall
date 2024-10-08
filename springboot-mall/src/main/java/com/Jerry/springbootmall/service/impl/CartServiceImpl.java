package com.Jerry.springbootmall.service.impl;

import com.Jerry.springbootmall.dao.CartDao;
import com.Jerry.springbootmall.dto.CartItemRequest;
import com.Jerry.springbootmall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    @Override
    public Integer addCart(Integer userId) {
       return cartDao.createCart(userId);

    }

    @Override
    public Integer createCartItem(Integer cartId, CartItemRequest cartItemRequest) {
        Integer cartItemId = cartDao.createCartItem(cartId, cartItemRequest);
        return cartItemId;
    }

    @Override
    public void deleteCartItem(Integer cartItemId) {
        cartDao.deleteCartItem(cartItemId);
    }
}