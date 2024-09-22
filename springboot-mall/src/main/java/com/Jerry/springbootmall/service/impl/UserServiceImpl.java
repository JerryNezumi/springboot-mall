package com.Jerry.springbootmall.service.impl;

import com.Jerry.springbootmall.dao.UserDao;
import com.Jerry.springbootmall.dto.UserRegisterRequest;
import com.Jerry.springbootmall.model.User;
import com.Jerry.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        Integer id = userDao.createUser(userRegisterRequest);
        return id;
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}