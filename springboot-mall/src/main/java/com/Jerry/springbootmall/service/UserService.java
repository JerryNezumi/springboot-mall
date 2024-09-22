package com.Jerry.springbootmall.service;

import com.Jerry.springbootmall.dto.UserRegisterRequest;
import com.Jerry.springbootmall.model.User;

public interface UserService {
    Integer register(UserRegisterRequest userRegisterRequest);
    User getUserById(Integer userId);
}
