package com.ijse.pos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ijse.pos.entity.Item;
import com.ijse.pos.entity.User;
import com.ijse.pos.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public  List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long userID) {
        return userRepository.findById(userID).get();
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userID) {
        userRepository.deleteById(userID);
    }

    @Override
    public User login(User user) {
        return userRepository.findByUsername(user.getUsername()).get();
    }

}
