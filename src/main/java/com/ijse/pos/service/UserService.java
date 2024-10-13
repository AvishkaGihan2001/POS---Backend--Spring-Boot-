package com.ijse.pos.service;

import org.springframework.stereotype.Service;

import com.ijse.pos.entity.User;

@Service
public interface UserService {

    User getUser(Long userID);

    User saveUser(User user);

    User updateUser(User user);

    void deleteUser(Long userID);

    
}
