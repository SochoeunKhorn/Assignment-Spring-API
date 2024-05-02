package com.sochoeun.mapper;

import com.sochoeun.dto.CourseDTO;
import com.sochoeun.entity.Course;
import com.sochoeun.service.CategoryService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",uses = {CategoryService.class})
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    Course toCourse(CourseDTO dto);

    @Mapping(target = "category",source = "category.id")
    CourseDTO toCourseDto(Course course);
}
