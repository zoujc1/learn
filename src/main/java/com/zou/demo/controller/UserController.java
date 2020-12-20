package com.zou.demo.controller;

import com.zou.demo.model.User;
import com.zou.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String findList() {
        return "k8s测试";
    }

    @PostMapping
/*    public User add(User user) {
        System.out.println(user.name);
        return userService.add(user);

    }*/
    public User add(@Valid @RequestBody User user) {
        return userService.add(user);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);

    }

    @PutMapping
    public User update(User user) {
        return userService.update(user);

    }

    @DeleteMapping
    public void delete(Long id) {
        userService.delete(id);

    }


}
