package com.sochoeun.service.impl;

import com.sochoeun.entity.Category;
import com.sochoeun.exception.NotFoundException;
import com.sochoeun.repository.CategoryRepository;
import com.sochoeun.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public Category create(Category req) {
        return  categoryRepository.save(req);
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(()->new NotFoundException("Category",id));
    }

    @Override
    public void update(Long id, Category req) {
        Category category = getCategory(id);
        category.setName(req.getName());
        categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        Category category = getCategory(id);
        categoryRepository.deleteById(id);
    }
}
