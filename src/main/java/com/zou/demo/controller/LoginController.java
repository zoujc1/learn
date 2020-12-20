package com.zou.demo.controller;


import com.zou.demo.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController {

    @PostMapping("/login")
    public String login(@Valid @RequestBody User user) {
        System.out.println("抛出controller");
        System.out.println(user.getName());
        return user.getName();
    }
}
