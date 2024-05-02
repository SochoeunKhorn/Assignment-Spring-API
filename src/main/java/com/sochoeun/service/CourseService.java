package com.sochoeun.service;

import com.sochoeun.dto.CourseDTO;
import com.sochoeun.entity.Course;

import java.util.List;

public interface CourseService {
    CourseDTO create(CourseDTO dto);
    List<CourseDTO> getCourses();
    CourseDTO getCourse(Long id);
    void update(Long id,CourseDTO dto);
    void delete(Long id);
}
