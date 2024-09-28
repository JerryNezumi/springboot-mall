package com.Jerry.springbootmall.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ActivateController {
    @GetMapping("/activate")
    public String activate() {
        return "啟動成功!";
    }

}