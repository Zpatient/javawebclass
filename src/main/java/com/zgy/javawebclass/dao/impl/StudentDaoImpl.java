package com.zgy.javawebclass.dao.impl;

import com.zgy.javawebclass.bean.Admin;
import com.zgy.javawebclass.bean.Student;
import com.zgy.javawebclass.bean.Teacher;
import com.zgy.javawebclass.dao.StudentDao;
import com.zgy.javawebclass.utils.DBUtil;

import java.sql.Connection;

/**
 * @author zgy
 * @create 2022-12-19 19:45
 */
public class StudentDaoImpl implements StudentDao{
    @Override
    public Student getById(Integer id) {
        Connection conn = DBUtil.getConn();
        Student student = DBUtil.getById(conn, id, Student.class);
        DBUtil.close(conn);
        return student;
    }
    @Override
    public Integer changePassword(Integer id, String newPassword) {
        String sql = "update student set password = ? where id = ?;";
        Object[] objects = {newPassword,id};
        Connection conn = DBUtil.getConn();
        Integer count = DBUtil.excuteUpdate(conn, sql, objects);
        DBUtil.close(conn);
        return count;
    }
    @Override
    public Integer update(Student student) {
        String sql = "update student set password = ?,name = ?,selected = ? where id = ?";
        Object[] objects = {student.getPassword(),student.getName(),student.getSelected(),student.getId()};
        Connection conn = DBUtil.getConn();
        Integer count = DBUtil.excuteUpdate(conn, sql, objects);
        DBUtil.close(conn);
        return count;
    }

}
