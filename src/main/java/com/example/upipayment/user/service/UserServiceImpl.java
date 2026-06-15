package com.example.upipayment.user.service;

import com.example.upipayment.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void createUser(String name, String mobile, String upiId) {
        System.out.println("User created: " + name + ", " + mobile + ", " + upiId);
    }
}
