package com.zgy.javawebclass.dao;

import com.zgy.javawebclass.bean.Course;

import java.util.List;

/**
 * @author zgy
 * @create 2022-12-18 11:12
 */
public interface CourseDao {
    List<Course> getAll();
}
