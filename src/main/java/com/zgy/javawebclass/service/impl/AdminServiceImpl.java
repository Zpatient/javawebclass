package com.zgy.javawebclass.service.impl;

import com.zgy.javawebclass.bean.Admin;
import com.zgy.javawebclass.dao.AdminDao;
import com.zgy.javawebclass.dao.impl.AdminDaoImpl;
import com.zgy.javawebclass.service.AdminService;

/**
 * @author zgy
 * @create 2022-12-18 16:57
 */
public class AdminServiceImpl implements AdminService {
    AdminDao adminDao = new AdminDaoImpl();
    @Override
    public Admin adminLogin(Integer id,String password) {
        Admin admin = adminDao.getById(id);
        if(admin==null) return null;
        String adminPassword = admin.getPassword();
        if(adminPassword.equals(password)) return admin;
        else return null;
    }

    @Override
    public Boolean adminChangePassword(Admin admin, String password) {
        if(admin==null) return false;
        Integer id = admin.getId();
        Integer count = adminDao.changePassword(id, password);
        if(count.equals(1)) return true;
        else return false;
    }
}
