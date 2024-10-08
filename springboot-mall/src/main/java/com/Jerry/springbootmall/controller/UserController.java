package com.Jerry.springbootmall.controller;

import com.Jerry.springbootmall.dto.UserRegisterRequest;
import com.Jerry.springbootmall.model.User;
import com.Jerry.springbootmall.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/User/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        Integer userId = userService.register(userRegisterRequest);

        User user = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);

    }

    @PostMapping("/User/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
        User user = userService.login(userRegisterRequest);
        return ResponseEntity.status(HttpStatus.OK).body(user);

    }
}