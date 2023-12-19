package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService {

    User getUserById(long id);

    List<User> getAllUsers();
}
