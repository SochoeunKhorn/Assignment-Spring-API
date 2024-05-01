package com.sochoeun.controller;

import com.sochoeun.dto.CategoryDTO;
import com.sochoeun.entity.Category;
import com.sochoeun.mapper.CategoryMapper;
import com.sochoeun.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CategoryDTO dto){
        Category category = CategoryMapper.INSTANCE.toCategory(dto);
        category = categoryService.create(category);
        return ResponseEntity.ok(CategoryMapper.INSTANCE.toCategoryDto(category));
    }

    @GetMapping
    public ResponseEntity<?> getCategories(){
        List<CategoryDTO> dto = categoryService.getCategories()
                .stream()
                //.map(c -> CategoryMapper.INSTANCE.toCategoryDto(c))
                .map(CategoryMapper.INSTANCE::toCategoryDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable("id") Long id){
        return ResponseEntity.ok(categoryService.getCategory(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id,@RequestBody CategoryDTO dto){
        Category category = CategoryMapper.INSTANCE.toCategory(dto);
        categoryService.update(id,category);
        return ResponseEntity.ok(CategoryMapper.INSTANCE.toCategoryDto(categoryService.getCategory(id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        categoryService.delete(id);
        return ResponseEntity.ok("Category ID : %s deleted".formatted(id));
    }


}
