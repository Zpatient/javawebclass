package com.zgy.javawebclass.service.impl;

import com.zgy.javawebclass.bean.Course;
import com.zgy.javawebclass.bean.CourseView;
import com.zgy.javawebclass.bean.Student;
import com.zgy.javawebclass.bean.Teacher;
import com.zgy.javawebclass.dao.TeacherDao;
import com.zgy.javawebclass.dao.impl.TeacherDaoImpl;
import com.zgy.javawebclass.service.CourseService;
import com.zgy.javawebclass.service.TeacherService;

import java.util.List;

/**
 * @author zgy
 * @create 2022-12-18 11:10
 */
public class TeacherServiceImpl implements TeacherService {
    TeacherDao teacherDao = new TeacherDaoImpl();
    CourseService courseService = new CourseServiceImpl();

    @Override
    public Teacher teacherLogin(Integer id, String password) {
        Teacher teacher = teacherDao.getById(id);
        if(teacher==null) return null;
        String teacherPassword = teacher.getPassword();
        if(teacherPassword.equals(password)) return teacher;
        else return null;
    }

    @Override
    public Boolean teacherChangePassword(Teacher teacher, String password) {
        if(teacher==null) return false;
        teacher.setPassword(password);
        Integer count = teacherDao.update(teacher);
        if(count.equals(1)) return true;
        else return false;
    }

    @Override
    public List<Teacher> getAll() {
        return teacherDao.getAll();
    }

    @Override
    public Boolean update(Teacher teacher) {
        Integer count = teacherDao.update(teacher);
        if(count.equals(1)) return true;
        else return false;
    }

    @Override
    public Boolean insert(Teacher teacher) {
        Integer count = teacherDao.insert(teacher);
        if(count.equals(1)) return true;
        else return false;
    }

    @Override
    public Boolean remove(Integer id) {
        if(id==null) return false;
        Integer count = teacherDao.remove(id);
        if(count.equals(1)) return true;
        else return false;
    }

    @Override
    public List<CourseView> getCourses(Integer teacherId) {
        if(teacherId==null) return null;
        List<CourseView> courseViews = courseService.getCourseViewsByTeacherId(teacherId);
        return courseViews;
    }
}
