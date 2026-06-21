package com.example.upipayment.user.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @PostMapping("/register")
    public String register() {
        return "Working";
    }
}
