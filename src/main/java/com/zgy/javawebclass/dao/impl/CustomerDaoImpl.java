package com.zgy.javawebclass.dao.impl;


import com.zgy.javawebclass.bean.Customer;
import com.zgy.javawebclass.dao.BaseDao;
import com.zgy.javawebclass.dao.CustomerDao;

import java.sql.ResultSet;
import java.util.List;

//dao实现类
public class CustomerDaoImpl extends BaseDao implements CustomerDao {
    @Override
    public int save(Customer customer) {
        String sql = "insert into customer values("+customer.getId()+",'"+customer.getName()+"',"+customer.getAge()+","+customer.getHeight()+",'"+customer.getPhoneNumber()+"')";
        int count = this.excuteUpdate(sql, null);
        return count;
    }
    @Override
    public List<Customer> getAll() throws Exception {
        String sql = "select * from customer";
        ResultSet resultSet = this.executeQuery(sql, null);
        List<Customer> customers = this.resultSetToBeanList(resultSet, Customer.class);
        return customers;
    }
    @Override
    public Customer getById(Integer id) throws Exception {
        String sql ="select * from customer where id = "+id;
        ResultSet resultSet = this.executeQuery(sql, null);
        List<Customer> customers = this.resultSetToBeanList(resultSet, Customer.class);
        return customers.get(0);
    }
    @Override
    public int removeById(Integer id){
        String sql ="delete from customer where id = "+id;
        int count = this.excuteUpdate(sql, null);
        return count;
    }
}
