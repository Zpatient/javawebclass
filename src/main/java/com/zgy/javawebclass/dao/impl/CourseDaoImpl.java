package com.zgy.javawebclass.dao.impl;

import com.zgy.javawebclass.bean.Course;
import com.zgy.javawebclass.bean.Course;
import com.zgy.javawebclass.dao.BaseDao;
import com.zgy.javawebclass.dao.CourseDao;
import com.zgy.javawebclass.utils.DBUtil;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zgy
 * @create 2022-12-18 11:12
 */
public class CourseDaoImpl extends BaseDao implements CourseDao {
    public List<Course> getAll() {
        Connection conn = DBUtil.getConn();
        String sql = "select * from course";
        List<Course> courses = DBUtil.getBySql(conn, sql, Course.class, null);
        DBUtil.close(conn);
        return courses;
    }

    @Override
    public Integer update(Course course) {
        String sql = "update course set name = ?,content = ?,teacherid = ?,score = ? where id = ?;";
        Object[] objects = {course.getName(),course.getContent(),course.getTeacherid(),course.getScore(),course.getId()};
        Connection conn = DBUtil.getConn();
        Integer count = DBUtil.excuteUpdate(conn, sql, objects);
        DBUtil.close(conn);
        return count;
    }

    @Override
    public Integer insert(Course course) {
        Connection conn = DBUtil.getConn();
        Integer count = DBUtil.save(conn, course);
        DBUtil.close(conn);
        return count;
    }

    @Override
    public Integer remove(Integer id) {
        Connection conn = DBUtil.getConn();
        Integer count = DBUtil.removeById(conn, id, Course.class);
        DBUtil.close(conn);
        return count;
    }
    @Override
    public Course getById(Integer id) {
        Connection conn = DBUtil.getConn();
        Course course = DBUtil.getById(conn, id, Course.class);
        DBUtil.close(conn);
        return course;
    }

    @Override
    public List<Course> getCourseByIds(List<Integer> ids) {
        if(ids==null) return null;
        ArrayList<Course> courses = new ArrayList<>();
        for(Integer id : ids){
            Course course = getById(id);
            if(course!=null)
                courses.add(course);
        }
        return courses;
    }

    @Override
    public List<Course> getCourseByTeacherId(Integer teacherId) {
        if(teacherId==null) return null;
        String sql = "select * from course where teacherId = "+teacherId;
        Connection conn = getConn();
        List<Course> courses = getBySql(conn, sql, Course.class);
        return courses;
    }

    @Override
    public Course getByName(String courseName) {
        String sql = "select * from course where name = '"+courseName+"'";
        Connection conn = getConn();
        List<Course> courses = getBySql(conn, sql, Course.class);
        if(courses.size()!=1) return null;
        else{
            Course course = courses.get(0);
            return course;
        }
    }
}
