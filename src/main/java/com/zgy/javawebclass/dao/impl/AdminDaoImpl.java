package com.zgy.javawebclass.dao.impl;

import com.zgy.javawebclass.bean.Admin;
import com.zgy.javawebclass.dao.AdminDao;
import com.zgy.javawebclass.utils.DBUtil;

import java.sql.Connection;

/**
 * @author zgy
 * @create 2022-12-18 16:46
 */
public class AdminDaoImpl implements AdminDao {
    @Override
    public Admin getById(Integer id) {
        Connection conn = DBUtil.getConn();
        Admin admin = DBUtil.getById(conn, id, Admin.class);
        DBUtil.close(conn);
        return admin;
    }

    @Override
    public Integer changePassword(Integer id, String newPassword) {
        String sql = "update admin set password = ? where id = ?;";
        Object[] objects = {newPassword,id};
        Connection conn = DBUtil.getConn();
        Integer count = DBUtil.excuteUpdate(conn, sql, objects);
        DBUtil.close(conn);
        return count;
    }
}
