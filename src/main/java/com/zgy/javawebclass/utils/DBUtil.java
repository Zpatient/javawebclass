package com.zgy.javawebclass.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zgy
 * @create 2022-12-08 19:11
 */
public class DBUtil {

    public static String url = "jdbc:mysql://rm-uf6dwiuhh0val89vaho.mysql.rds.aliyuncs.com:3306/db_tourist?useSSL=false";
    public static String user = "chen_1817030415";
    public static String password = "chen_1817030415";

    class Parameter{
        private Integer type;//0:String,1:Integer,2:Double,3:DateTime
        private String value;
        Parameter(Integer type,String value){
            this.type = type;
            this.value = value;
        }
        Parameter(){}
        public Integer getType() {
            return type;
        }
        public String getValue() {
            return value;
        }
    }
    public static void init(String user,String password,String url){
        DBUtil.user = user;
        DBUtil.password = password;
        DBUtil.url = url;
    }
    public static Connection getConn() {
        Connection conn = null;
        String driver = "com.mysql.jdbc.Driver";
        try {
            //加载数据库驱动
            Class.forName(driver);
            //获取数据库连接
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return conn;
    }
    public static <T> T getById(Integer id,Class<T> clazz) throws Exception {
        String name = clazz.getName();
        String sql = "select * from "+name.toLowerCase()+" where id = "+id;
        List<T> list = get(sql, null, clazz);
        T t = list.get(0);
        return t;
    }
    public static <T> List<T> get(String sql,List<Parameter> params,Class<T> clazz) throws Exception {
        if(sql==null||clazz==null) return null;
        ResultSet resultSet = select(sql, params);
        List<T> beanList = resultSetToBeanList(resultSet, clazz);
        return beanList;
    }
    public static <T> List<T> resultSetToBeanList(ResultSet resultSet, Class<T> clazz) throws Exception {
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
    public static ResultSet select(String sql,List<Parameter> params){
        Connection conn = getConn();
        try {
            PreparedStatement preStmt = getPreStmt(conn,sql, params);
            ResultSet resultSet = preStmt.executeQuery();
            conn.close();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static PreparedStatement getPreStmt(Connection connection,String sql,List<Parameter> params) throws SQLException{
        if(connection==null) return null;
        PreparedStatement preStatement = connection.prepareStatement(sql);
        if(params!=null&&params.size()>0){
            int size = params.size();
            for(Integer i = 1;i<=size;i++){
                Parameter parameter = params.get(i - 1);
                if(parameter.getType().equals(0))
                    preStatement.setString(i,parameter.getValue());
                else if(parameter.getType().equals(1))
                    preStatement.setInt(i,Integer.parseInt(parameter.getValue()));
                else if(parameter.getType().equals(2))
                    preStatement.setDouble(i,Double.parseDouble(parameter.getValue()));
            }
        }
        return preStatement;
    }
}
