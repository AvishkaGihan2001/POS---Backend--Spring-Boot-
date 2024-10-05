package com.ijse.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.pos.entity.Item;

@Service
public interface ItemService {
    List<Item> getItemList();

    Item getItem(Long itemID);

    Item saveItem(Item item);
    
    Item updateItem(Item item);

    void deleteItem(Long itemID);
}
