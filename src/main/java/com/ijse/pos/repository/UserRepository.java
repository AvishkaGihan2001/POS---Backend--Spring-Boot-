package com.ijse.pos.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ijse.pos.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
