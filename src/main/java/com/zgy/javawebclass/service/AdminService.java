package com.zgy.javawebclass.service;

import com.zgy.javawebclass.bean.Admin;

/**
 * @author zgy
 * @create 2022-12-18 16:50
 */
public interface AdminService {
    Admin adminLogin(Integer id,String password);
    Boolean adminChangePassword(Admin admin,String password);
}
