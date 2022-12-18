package com.zgy.javawebclass.dao.impl;

import com.zgy.javawebclass.bean.Course;
import com.zgy.javawebclass.dao.CourseDao;
import com.zgy.javawebclass.utils.DBUtil;

import java.sql.Connection;
import java.util.List;

/**
 * @author zgy
 * @create 2022-12-18 11:12
 */
public class CourseDaoImpl implements CourseDao {
    public List<Course> getAll() {
        Connection conn = DBUtil.getConn();
        String sql = "select * from course";
        List<Course> courses = DBUtil.getBySql(conn, sql, Course.class, null);
        DBUtil.close(conn);
        return courses;
    }
}
