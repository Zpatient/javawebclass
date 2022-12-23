package com.zgy.javawebclass.dao;

import com.zgy.javawebclass.bean.Course;

import java.util.List;

/**
 * @author zgy
 * @create 2022-12-18 11:12
 */
public interface CourseDao {
    List<Course> getAll();
    Integer update(Course course);
    Integer insert(Course course);
    Integer remove(Integer id);
    Course getById(Integer id);
    List<Course> getCourseByIds(List<Integer> ids);
    List<Course> getCourseByTeacherId(Integer teacherId);
    Course getByName(String courseName);
}
