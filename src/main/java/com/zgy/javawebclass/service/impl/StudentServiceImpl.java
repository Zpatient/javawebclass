package com.zgy.javawebclass.service.impl;

import com.zgy.javawebclass.bean.*;
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
        if(courseViews!=null){
            for(CourseView courseView:courseViews){
                Integer courseId = courseView.getId();
                Selection selection = selectionService.getSelection(id, courseId);
                if(selection!=null){
                    if(selection.getSee().equals(0))
                        courseView.setStudentShow(false);
                    else
                        courseView.setStudentShow(true);
                }
            }
        }
        return courseViews;
    }

    @Override
    public Student getById(Integer id) {
        if (id == null) return null;
        Student student = studentDao.getById(id);
       return student;
    }

    @Override
    public Boolean register(StudentRegister studentRegister) {
        if(studentRegister == null) return false;
        if(!studentRegister.getConfirmPassword().equals(studentRegister.getPassword()))
            return false;
        Student student = new Student();
        student.setId(studentRegister.getId());
        student.setName(studentRegister.getName());
        student.setPassword(studentRegister.getPassword());
        student.setSelected(0);
        Integer count = studentDao.insertStudent(student);
        if(count.equals(1)) return true;
        else return false;

    }
}
