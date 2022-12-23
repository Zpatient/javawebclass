package com.zgy.javawebclass.service;

import com.zgy.javawebclass.bean.Course;
import com.zgy.javawebclass.bean.CourseView;
import com.zgy.javawebclass.bean.Student;
import com.zgy.javawebclass.bean.Teacher;

import java.util.List;

/**
 * @author zgy
 * @create 2022-12-18 11:09
 */
public interface TeacherService {
    Teacher teacherLogin(Integer id, String password);
    Boolean teacherChangePassword(Teacher teacher,String password);
    List<Teacher> getAll();
    Boolean update(Teacher teacher);
    Boolean insert(Teacher teacher);
    Boolean remove(Integer id);
    List<CourseView> getCourses(Integer teacherId);
}
