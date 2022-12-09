package com.zgy.javawebclass.dao;


import com.zgy.javawebclass.bean.Customer;

import java.util.List;

public interface CustomerDao {
    int save(Customer customer);
    List<Customer> getAll() throws Exception;
    public Customer getById(Integer id) throws Exception;
    public int removeById(Integer id);
}
