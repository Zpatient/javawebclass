package com.zgy.javawebclass.service;

import com.zgy.javawebclass.bean.Course;
import com.zgy.javawebclass.bean.CourseView;

import java.util.List;

/**
 * @author zgy
 * @create 2022-12-18 11:09
 */
public interface CourseService {
    List<Course> getAll();
    Boolean update(Course course);
    Boolean insert(Course course);
    Boolean remove(Integer id);
    List<CourseView> getCourseViews(List<Integer> ids);
}
