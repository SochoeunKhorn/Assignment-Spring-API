package com.sochoeun.controller;

import com.sochoeun.dto.CourseDTO;
import com.sochoeun.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    @GetMapping
    public ResponseEntity<?> getCourses(){
        return ResponseEntity.ok(courseService.getCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourse(@PathVariable("id")Long id){
        return ResponseEntity.ok(courseService.getCourse(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CourseDTO dto){
        return ResponseEntity.ok(courseService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id")Long id,@RequestBody CourseDTO dto){
        courseService.update(id,dto);
        return ResponseEntity.ok(getCourse(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id){
        courseService.delete(id);
        return ResponseEntity.ok("Course ID :%s deleted".formatted(id));
    }
}
