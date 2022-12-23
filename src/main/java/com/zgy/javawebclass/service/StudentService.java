package com.zgy.javawebclass.service;

import com.zgy.javawebclass.bean.Admin;
import com.zgy.javawebclass.bean.CourseView;
import com.zgy.javawebclass.bean.Student;
import com.zgy.javawebclass.bean.StudentRegister;

import java.util.List;

/**
 * @author zgy
 * @create 2022-12-18 16:50
 */
public interface StudentService {
    Student studentLogin(Integer id, String password);
    Boolean studentChangePassword(Student student,String password);
    Boolean update(Student student);
    List<CourseView> getCourses(Integer id);
    Student getById(Integer id);
    Boolean register(StudentRegister studentRegister);
}
