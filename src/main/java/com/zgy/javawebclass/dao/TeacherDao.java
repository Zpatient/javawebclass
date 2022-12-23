package com.zgy.javawebclass.dao;

import com.zgy.javawebclass.bean.Course;
import com.zgy.javawebclass.bean.Teacher;

import java.util.List;

/**
 * @author zgy
 * @create 2022-12-18 11:12
 */
public interface TeacherDao {
    List<Teacher> getAll();
    Integer update(Teacher teacher);
    Integer insert(Teacher teacher);
    Integer remove(Integer id);
    Teacher getById(Integer id);
}
