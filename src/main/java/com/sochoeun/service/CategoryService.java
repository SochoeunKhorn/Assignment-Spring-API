package com.sochoeun.service;

import com.sochoeun.entity.Category;

import java.util.List;

public interface CategoryService {
    Category create(Category req);
    List<Category> getCategories();
    Category getCategory(Long id);
    void update(Long id, Category req);
    void delete(Long id);
}
