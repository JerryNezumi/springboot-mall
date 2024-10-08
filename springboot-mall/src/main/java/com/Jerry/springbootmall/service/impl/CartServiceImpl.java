package com.Jerry.springbootmall.service.impl;

import com.Jerry.springbootmall.dao.CartDao;
import com.Jerry.springbootmall.dao.UserDao;
import com.Jerry.springbootmall.dto.CartItemRequest;
import com.Jerry.springbootmall.model.CartItem;
import com.Jerry.springbootmall.model.User;
import com.Jerry.springbootmall.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private UserDao userDao;

    private final static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

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

    @Override
    public List<CartItem> getCartItems(Integer userId) {
        User user = userDao.getUserById(userId);
        if (user == null) {
            logger.warn("該{}User尚未註冊", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return cartDao.getCartItems(userId);

    }

    @Transactional
    @Override
    public CartItem updateCartItem(Integer cartItemId, CartItemRequest cartItemRequest) {
        cartDao.updateCartItem(cartItemId, cartItemRequest);
        return cartDao.getCartItemById(cartItemId);
    }
}
