package com.ijse.pos.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.ijse.pos.entity.Category;

@Service
public interface CategoryService {
    List<Category> getCategoryList();

    Category getCategory(Long categoryID);

    Category saveCategory(Category category);

    Category updateCategory(Category category);

    void deleteCategory(Long categoryID);
}
