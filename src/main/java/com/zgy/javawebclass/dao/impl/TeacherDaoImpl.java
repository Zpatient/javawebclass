package com.zgy.javawebclass.dao.impl;

import com.zgy.javawebclass.bean.Course;
import com.zgy.javawebclass.bean.Teacher;
import com.zgy.javawebclass.dao.CourseDao;
import com.zgy.javawebclass.dao.TeacherDao;
import com.zgy.javawebclass.utils.DBUtil;

import java.sql.Connection;
import java.util.List;

/**
 * @author zgy
 * @create 2022-12-18 11:12
 */
public class TeacherDaoImpl implements TeacherDao {
    public List<Teacher> getAll() {
        Connection conn = DBUtil.getConn();
        String sql = "select * from teacher";
        List<Teacher> teachers = DBUtil.getBySql(conn, sql, Teacher.class, null);
        DBUtil.close(conn);
        return teachers;
    }
}
