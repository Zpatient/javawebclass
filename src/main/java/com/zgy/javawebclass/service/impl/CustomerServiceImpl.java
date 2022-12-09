package com.zgy.javawebclass.service.impl;

import com.zgy.javawebclass.bean.Customer;
import com.zgy.javawebclass.dao.BaseDao;
import com.zgy.javawebclass.dao.CustomerDao;
import com.zgy.javawebclass.dao.impl.CustomerDaoImpl;
import com.zgy.javawebclass.service.CustomerService;

import java.util.List;

/**
 * @author zgy
 * @create 2022-12-09 14:01
 */
public class CustomerServiceImpl implements CustomerService {
    CustomerDao customerDao = new CustomerDaoImpl();
    @Override
    public Customer search(Integer id){
        Customer customer = null;
        try {
            customer = customerDao.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }
    @Override
    public void delete(Integer id) {
        int i = customerDao.removeById(id);
    }
    @Override
    public List<Customer> getList() {
        List<Customer> all = null;
        try {
            all = customerDao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return all;

    }
    @Override
    public void add(Integer id, String name, Integer age, String phoneNumber) {
        Customer customer = new Customer(id, name, age, 175.5, phoneNumber);
        int save = customerDao.save(customer);
    }
}
