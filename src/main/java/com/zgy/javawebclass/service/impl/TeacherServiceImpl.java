package com.zgy.javawebclass.service.impl;

import com.zgy.javawebclass.bean.Teacher;
import com.zgy.javawebclass.dao.TeacherDao;
import com.zgy.javawebclass.dao.impl.TeacherDaoImpl;
import com.zgy.javawebclass.service.TeacherService;

import java.util.List;

/**
 * @author zgy
 * @create 2022-12-18 11:10
 */
public class TeacherServiceImpl implements TeacherService {
    TeacherDao teacherDao = new TeacherDaoImpl();
    @Override
    public List<Teacher> getAll() {
        return teacherDao.getAll();
    }
}
