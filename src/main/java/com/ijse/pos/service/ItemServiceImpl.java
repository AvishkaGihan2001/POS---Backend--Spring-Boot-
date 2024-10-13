package com.ijse.pos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.pos.entity.Item;
import com.ijse.pos.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> getItemList() {
        return itemRepository.findAll();
    }

    @Override
    public Item getItem(Long itemID) {
        return itemRepository.findById(itemID).get();
    }

    @Override
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item updateItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void deleteItem(Long itemID) {
        itemRepository.deleteById(itemID);
    }

}
