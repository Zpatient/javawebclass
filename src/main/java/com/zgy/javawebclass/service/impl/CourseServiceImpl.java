package com.zgy.javawebclass.service.impl;

import com.zgy.javawebclass.bean.Course;
import com.zgy.javawebclass.dao.CourseDao;
import com.zgy.javawebclass.dao.impl.CourseDaoImpl;
import com.zgy.javawebclass.service.CourseService;

import java.util.List;

/**
 * @author zgy
 * @create 2022-12-18 11:10
 */
public class CourseServiceImpl implements CourseService {
    CourseDao courseDao = new CourseDaoImpl();
    @Override
    public List<Course> getAll() {
        return courseDao.getAll();
    }
}
