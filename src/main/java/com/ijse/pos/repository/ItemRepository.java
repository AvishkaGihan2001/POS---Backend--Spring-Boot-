package com.ijse.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.pos.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}