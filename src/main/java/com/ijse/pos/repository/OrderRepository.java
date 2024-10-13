package com.ijse.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ijse.pos.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
