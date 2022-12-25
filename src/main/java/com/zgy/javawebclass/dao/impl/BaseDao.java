package com.zgy.javawebclass.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author zgy
 * @create 2022-12-08 19:11
 */
public class BaseDao {

    public String driverClass;
    public String url;
    public String user;
    public String password;

    /**
     * 初始化配置信息
     * @author: ZGY
     * @return void
     */
    public  void init(){

        Properties prop = new Properties();
        try {
            InputStream resource = BaseDao.class.getClassLoader().getResourceAsStream("jdbc.properties");
            prop.load(resource);
            driverClass = prop.getProperty("driverClass");
            url = prop.getProperty("url");
            user = prop.getProperty("user");
            password = prop.getProperty("password");
            resource.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取连接
     * @return java.sql.Connection
     */
    public  Connection getConn() {
        Connection conn = null;
        init();
        try {
            //加载数据库驱动
            Class.forName(driverClass);
            //获取数据库连接
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return conn;
    }
    /**
     * 关闭连接
     * @param connection 待关闭的连接
     * @return void
     */
    public void close(Connection connection){
        if(connection==null) return;
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 向表中添加一条记录（表名与bean参数类名在忽略大小写情况下要一致）
     * @param connection 连接
     * @param bean 插入的bean
     * @return java.lang.Integer
     */
    public <T> Integer save(Connection connection,T bean){
        if(bean==null) return 0;
        Class<T> clazz = (Class<T>) bean.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Object [] objects = new Object[fields.length];
        String sql = "insert into "+clazz.getSimpleName().toLowerCase()+" values(";
        try {
            for(int i = 0;i<fields.length;i++){
                if(i!=fields.length-1){
                    sql+="?,";
                }
                else {
                    sql+="?);";
                }
                String fieldName = fields[i].getName();
                Method getField = clazz.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), null);
                Object attribute = getField.invoke(bean, null);
                objects[i] = attribute;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Integer count = excuteUpdate(connection, sql, objects);
        return count;
    }
    public <T> Integer removeById(Connection connection,Integer id,Class<T> clazz){
        if(id==null||clazz==null) return 0;
        String sql = "delete from "+clazz.getSimpleName().toLowerCase()+" where id = "+id;
        Integer count = excuteUpdate(connection, sql, null);
        return count;
    }
    public Integer excuteUpdate(Connection connection,String sql,Object...objects) {
        if(sql==null) return 0;
        Integer count = 0;
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
    public <T> T getById(Connection connection,Integer id,Class<T> clazz){
        String name = clazz.getSimpleName();
        String sql = "select * from "+name.toLowerCase()+" where id = "+id;
        List<T> list = getBySql(connection,sql, clazz,null);
        if(list.size()==0) return null;
        else{
            T bean = list.get(0);
            return bean;
        }
    }
    public <T> List<T> getBySql(Connection connection,String sql,Class<T> clazz,Object... objects){
        if(sql==null||clazz==null) return null;
        ResultSet resultSet = executeQuery(connection,sql, objects);
        List<T> beanList = resultSetToBeanList(resultSet, clazz);
        return beanList;
    }
    public ResultSet executeQuery(Connection connection,String sql,Object... objects){
        try {
            PreparedStatement preStmt = getPreStmt(connection,sql, objects);
            ResultSet resultSet = preStmt.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public <T> List<T> resultSetToBeanList(ResultSet resultSet, Class<T> clazz) {
        if(resultSet==null||clazz==null) return null;
        // 获取Bean对象内的所有属性
        Field[] fields = clazz.getDeclaredFields();
        List<T> beanList = new ArrayList<>();
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return beanList;
    }
    public PreparedStatement getPreStmt(Connection connection, String sql, Object... objects){
        if(connection==null) return null;
        PreparedStatement preStatement = null;
        try {
            preStatement = connection.prepareStatement(sql);
            if(objects!=null){
                for(int i=0;i<objects.length;i++)
                    preStatement.setObject(i+1,objects[i]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preStatement;
    }
}
