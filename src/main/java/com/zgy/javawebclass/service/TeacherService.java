package com.zgy.javawebclass.service;

import com.zgy.javawebclass.bean.Course;
import com.zgy.javawebclass.bean.Teacher;

import java.util.List;

/**
 * @author zgy
 * @create 2022-12-18 11:09
 */
public interface TeacherService {
    List<Teacher> getAll();
    Boolean update(Teacher teacher);
    Boolean insert(Teacher teacher);
    Boolean remove(Integer id);
}
