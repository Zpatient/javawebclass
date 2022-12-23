package com.zgy.javawebclass.dao.impl;

import com.zgy.javawebclass.bean.Teacher;
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

    @Override
    public Integer update(Teacher teacher) {
        String sql = "update teacher set password = ?,name = ?,title = ?,brief = ? where id = ?";
        Object[] objects = {teacher.getPassword(),teacher.getName(),teacher.getTitle(),teacher.getBrief(),teacher.getId()};
        Connection conn = DBUtil.getConn();
        Integer count = DBUtil.excuteUpdate(conn, sql, objects);
        DBUtil.close(conn);
        return count;
    }

    @Override
    public Integer insert(Teacher teacher) {
        Connection conn = DBUtil.getConn();
        Integer count = DBUtil.save(conn, teacher);
        DBUtil.close(conn);
        return count;
    }

    @Override
    public Integer remove(Integer id) {
        Connection conn = DBUtil.getConn();
        Integer count = DBUtil.removeById(conn, id, Teacher.class);
        DBUtil.close(conn);
        return count;
    }

    @Override
    public Teacher getById(Integer id) {
        Connection conn = DBUtil.getConn();
        Teacher teacher = DBUtil.getById(conn, id, Teacher.class);
        DBUtil.close(conn);
        return teacher;
    }
}
