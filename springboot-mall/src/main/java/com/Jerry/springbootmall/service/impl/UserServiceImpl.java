package com.Jerry.springbootmall.service.impl;

import com.Jerry.springbootmall.dao.UserDao;
import com.Jerry.springbootmall.dto.UserRegisterRequest;
import com.Jerry.springbootmall.model.User;
import com.Jerry.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {

        //判斷資料庫中是否已有重複的email
        User userByEmail = userDao.getUserByEmail(userRegisterRequest.getEmail());
        if (userByEmail != null) {
            log.warn("該email {} 已經被註冊", userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //創建帳號
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}