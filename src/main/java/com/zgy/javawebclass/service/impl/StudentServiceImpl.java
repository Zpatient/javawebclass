package com.zgy.javawebclass.service.impl;

import com.zgy.javawebclass.bean.Admin;
import com.zgy.javawebclass.bean.CourseView;
import com.zgy.javawebclass.bean.Student;
import com.zgy.javawebclass.dao.AdminDao;
import com.zgy.javawebclass.dao.StudentDao;
import com.zgy.javawebclass.dao.impl.AdminDaoImpl;
import com.zgy.javawebclass.dao.impl.StudentDaoImpl;
import com.zgy.javawebclass.service.AdminService;
import com.zgy.javawebclass.service.CourseService;
import com.zgy.javawebclass.service.SelectionService;
import com.zgy.javawebclass.service.StudentService;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;

/**
 * @author zgy
 * @create 2022-12-18 16:57
 */
public class StudentServiceImpl implements StudentService {
    StudentDao studentDao = new StudentDaoImpl();
    SelectionService selectionService = new SelectionServiceImpl();
    CourseService courseService = new CourseServiceImpl();
    @Override
    public Student studentLogin(Integer id, String password) {
        Student student = studentDao.getById(id);
        if(student==null) return null;
        String studentPassword = student.getPassword();
        if(studentPassword.equals(password)) return student;
        else return null;
    }

    @Override
    public Boolean studentChangePassword(Student student, String password) {
        if(student==null) return false;
        Integer id = student.getId();
        Integer count = studentDao.changePassword(id, password);
        if(count.equals(1)) return true;
        else return false;
    }
    @Override
    public Boolean update(Student student) {
        if(student==null)return null;
        Integer count = studentDao.update(student);
        if(count.equals(1))return true;
        else return false;
    }

    @Override
    public List<CourseView> getCourses(Integer id) {
        if(id==null) return null;
        List<Integer> courseIds = selectionService.getCourseIds(id);
        if(courseIds==null)return null;
        List<CourseView> courseViews = courseService.getCourseViews(courseIds);
        return courseViews;
    }

    @Override
    public Student getById(Integer id) {
        if (id == null) return null;
        Student student = studentDao.getById(id);
       return student;
    }
}
