package com.zgy.javawebclass.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDao{

    private static String driver = "com.mysql.cj.jdbc.Driver";
    public static String url = "jdbc:mysql://localhost:3306/jdbc?&useSSL=false&serverTimezone=UTC";
    public static String username = "root";
    public static String password = "123456";

    //获取数据库连接
    public Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //执行变更
    public int excuteUpdate(String sql,Object...objects) {
        int count = 0;
        Connection connection = getConnection();
        PreparedStatement ps = null;
        try {
            //设置SQL参数
            ps = connection.prepareStatement(sql);
            if(objects!=null){
                for(int i=0;i<objects.length;i++)
                    ps.setObject(i+1,objects[i]);
            }
            count = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    //执行查询
    public ResultSet executeQuery(String sql,Object...objects){
        Connection connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            if(objects!=null){
                for(int i=0;i<objects.length;i++)
                    ps.setObject(i+1,objects[i]);
            }
            ResultSet resultSet = ps.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //resultSet转Bean
    public <T> List<T> resultSetToBeanList(ResultSet resultSet, Class<T> clazz) throws Exception {
        if(resultSet==null||clazz==null) return null;
        // 获取Bean对象内的所有属性
        Field[] fields = clazz.getDeclaredFields();
        List<T> beanList = new ArrayList<>();
        if (resultSet != null) {
            while (resultSet.next()) {
                // 每当有一行数据就创建一个Bean对象
                T bean = clazz.newInstance();
                for (int i = 0; i < fields.length; i++) {
                    //访问private属性
                    fields[i].setAccessible(true);
                    String fieldName = fields[i].getName();
                    // 利用字符串拼接，将属性名的首字母变为大写，获取对应的set方法。
                    Method setField = clazz.getMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), fields[i].getType());
                    // 需保证数据库表字段顺序与bean一致
                    setField.invoke(bean,resultSet.getObject(i+1));
                }
                beanList.add(bean);
            }
        }
        return beanList;
    }

    //释放连接
    public void closeAll(ResultSet rs, Statement stmt, Connection con) {
        if(null != rs) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(null != stmt) {
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(null != con) {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
