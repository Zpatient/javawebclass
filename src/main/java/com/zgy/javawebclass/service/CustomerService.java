package com.zgy.javawebclass.service;

import com.zgy.javawebclass.bean.Customer;

import java.util.List;

/**
 * @author zgy
 * @create 2022-12-09 14:00
 */
public interface CustomerService {
    Customer search(Integer id);
    void delete(Integer id);
    List<Customer> getList();
    void add(Integer id,String name,Integer age,String phoneNumber);
}
