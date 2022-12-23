package com.zgy.javawebclass.dao;

import com.zgy.javawebclass.bean.Student;

/**
 * @author zgy
 * @create 2022-12-19 19:44
 */
public interface StudentDao {
    Student getById(Integer id);
    Integer changePassword(Integer id,String newPassword);
    Integer update(Student student);
}
