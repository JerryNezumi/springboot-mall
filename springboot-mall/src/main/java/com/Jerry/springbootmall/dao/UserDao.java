package com.Jerry.springbootmall.dao;

import com.Jerry.springbootmall.dto.UserRegisterRequest;
import com.Jerry.springbootmall.model.User;

public interface UserDao {
    Integer createUser(UserRegisterRequest userRegisterRequest);
    User getUserById(Integer userId);
    User getUserByEmail(String email);
}
