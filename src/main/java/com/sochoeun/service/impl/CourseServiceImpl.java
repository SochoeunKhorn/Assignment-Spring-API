package com.sochoeun.service.impl;

import com.sochoeun.dto.CourseDTO;
import com.sochoeun.entity.Category;
import com.sochoeun.entity.Course;
import com.sochoeun.exception.NotFoundException;
import com.sochoeun.mapper.CourseMapper;
import com.sochoeun.repository.CourseRepository;
import com.sochoeun.service.CategoryService;
import com.sochoeun.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CategoryService categoryService;
    private final CourseMapper courseMapper;
    @Override
    public CourseDTO create(CourseDTO dto) {
        // check category id exist or not
        categoryService.getCategory(dto.getCategory());

        Course course = courseMapper.toCourse(dto);
        course = courseRepository.save(course);
        return CourseMapper.INSTANCE.toCourseDto(course);
    }

    @Override
    public List<CourseDTO> getCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseDTO> courseDto = courses.stream()
                .map(CourseMapper.INSTANCE::toCourseDto)
                .collect(Collectors.toList());
        return courseDto;
    }

    @Override
    public CourseDTO getCourse(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course", id));
        return CourseMapper.INSTANCE.toCourseDto(course);
    }

    @Override
    public void update(Long id, CourseDTO dto) {
        Category category = categoryService.getCategory(dto.getCategory());
        Course course = courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course", id));
        course.setName(dto.getName());
        course.setCategory(category);
        courseRepository.save(course);

    }

    @Override
    public void delete(Long id) {
        if(getCourse(id) != null){
            courseRepository.deleteById(id);
        }
    }
}
