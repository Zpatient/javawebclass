package com.zgy.javawebclass.dao;

import com.zgy.javawebclass.bean.Admin;

/**
 * @author zgy
 * @create 2022-12-18 16:46
 */
public interface AdminDao {
    Admin getById(Integer id);
    Integer changePassword(Integer id,String newPassword);
}
