package com.ijse.pos.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ijse.pos.dto.ItemRequestDto;
import com.ijse.pos.entity.Category;
import com.ijse.pos.entity.Item;
import com.ijse.pos.service.CategoryService;
import com.ijse.pos.service.ItemService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin(origins = "*")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getItemList() {
        List<Item> items = itemService.getItemList();
        return ResponseEntity.status(200).body(items);
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id) {
        Item item = itemService.getItem(id);
        return ResponseEntity.status(200).body(item);
    }

    @PostMapping("/item")
    public ResponseEntity<?> saveItem(@RequestBody ItemRequestDto itemRequestDto) {
        Item item = new Item();
        item.setName(itemRequestDto.getName());
        item.setDescription(itemRequestDto.getDescription());
        item.setPrice(itemRequestDto.getPrice());
        item.setQuantity(itemRequestDto.getQuantity());

        Category category = categoryService.getCategory(itemRequestDto.getCategoryID());
        if (category == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Category not found for ID: " + itemRequestDto.getCategoryID());
        }

        item.setCategory(category);
        Item savedItem = itemService.saveItem(item);

        return ResponseEntity.ok(savedItem);
    }

    @PutMapping("/item/{id}")
    public ResponseEntity<?> updateItem(@PathVariable Long id, @RequestBody ItemRequestDto itemRequestDto) {
        Item item = itemService.getItem(id);
        if (item == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        item.setName(itemRequestDto.getName());
        item.setDescription(itemRequestDto.getDescription());
        item.setPrice(itemRequestDto.getPrice());
        item.setQuantity(itemRequestDto.getQuantity());

        Category category = categoryService.getCategory(itemRequestDto.getCategoryID());
        if (category == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Category not found for ID: " + itemRequestDto.getCategoryID());
        }

        item.setCategory(category);
        Item updatedItem = itemService.updateItem(item);

        return ResponseEntity.ok(updatedItem);
    }
        

    @DeleteMapping("/item/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.status(204).build();
    }

}
