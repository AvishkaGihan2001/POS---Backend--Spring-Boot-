package com.ijse.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.pos.entity.User;

@Service
public interface UserService {

    List<User> getAllUsers();

    User getUser(Long userID);

    User saveUser(User user);

    User updateUser(User user);

    void deleteUser(Long userID);

    User login(User user);

    
}
