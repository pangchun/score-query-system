package com.bs.controller;

import com.bs.model.User;
import com.bs.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    HelloService helloService;

    @PostMapping("/hello")

    public List<User> hello(@RequestBody User user){
        return helloService.hello(user);
    }
}
