package com.zgy.javawebclass.service.impl;

import com.zgy.javawebclass.bean.Course;
import com.zgy.javawebclass.bean.CourseView;
import com.zgy.javawebclass.bean.Teacher;
import com.zgy.javawebclass.dao.CourseDao;
import com.zgy.javawebclass.dao.TeacherDao;
import com.zgy.javawebclass.dao.impl.CourseDaoImpl;
import com.zgy.javawebclass.dao.impl.TeacherDaoImpl;
import com.zgy.javawebclass.service.CourseService;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zgy
 * @create 2022-12-18 11:10
 */
public class CourseServiceImpl implements CourseService {
    CourseDao courseDao = new CourseDaoImpl();
    TeacherDao teacherDao = new TeacherDaoImpl();
    @Override
    public List<Course> getAll() {
        return courseDao.getAll();
    }

    @Override
    public Boolean update(Course course) {
        Integer count = courseDao.update(course);
        if(count.equals(1)) return true;
        else return false;
    }

    @Override
    public Boolean insert(Course course) {
        Integer count = courseDao.insert(course);
        if(count.equals(1)) return true;
        else return false;
    }

    @Override
    public Boolean remove(Integer id) {
        if(id==null) return false;
        Integer count = courseDao.remove(id);
        if(count.equals(1)) return true;
        else return false;
    }

    @Override
    public List<CourseView> getCourseViewsByTeacherId(Integer teacherId) {
        if(teacherId==null) return null;
        List<Course> courses = courseDao.getCourseByTeacherId(teacherId);
        List<CourseView> courseViews=new ArrayList<>();
        for(Course course:courses){
            Integer teacherid = course.getTeacherid();
            Teacher teacher = teacherDao.getById(teacherid);
            if(teacher==null) continue;
            String teacherName = teacher.getName();
            CourseView courseView = new CourseView();
            courseView.setContent(course.getContent());
            courseView.setName(course.getName());
            courseView.setTeacher(teacherName);
            courseView.setScore(course.getScore());
            courseView.setId(course.getId());
            courseViews.add(courseView);
        }
        return courseViews;
    }

    @Override
    public List<CourseView> getCourseViews(List<Integer> ids) {
        if(ids==null) return null;
        List<Course> courses = courseDao.getCourseByIds(ids);
        List<CourseView> courseViews=new ArrayList<>();
        for(Course course:courses){
            Integer teacherid = course.getTeacherid();
            Teacher teacher = teacherDao.getById(teacherid);
            if(teacher==null) continue;
            String teacherName = teacher.getName();
            CourseView courseView = new CourseView();
            courseView.setContent(course.getContent());
            courseView.setName(course.getName());
            courseView.setTeacher(teacherName);
            courseView.setScore(course.getScore());
            courseView.setId(course.getId());
            courseViews.add(courseView);
        }
        return courseViews;
    }

    @Override
    public Boolean remark(String courseName, Integer score) {
        if(ObjectUtils.anyNull(courseName,score)) return false;
        Course byName = courseDao.getByName(courseName);
        if(byName!=null){
            Integer finalscore =(byName.getScore()*8+score*2)/10;
            byName.setScore(finalscore);
            Integer update = courseDao.update(byName);
            if(update.equals(1)) return true;
            else return false;
        }
        else return false;
    }
}
