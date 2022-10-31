package com.zgy.javawebclass.bean;
import javax.servlet.http.HttpSession;
public class User {
    //静态内部类 封装用户的基本信息
    public static class UserBaseInfo{
        String name;
        String sex;

        public UserBaseInfo(String name, String sex) {
            this.name = name;
            this.sex = sex;
        }

        public String getName() {
            return name;
        }

        public String getSex() {
            return sex;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
    //用户基本信息
    UserBaseInfo userBaseInfo;
    //用户Session
    HttpSession session;

    public User(UserBaseInfo userBaseInfo, HttpSession session) {
        this.userBaseInfo = userBaseInfo;
        this.session = session;
    }

    public UserBaseInfo getUserBaseInfo() {
        return userBaseInfo;
    }

    public HttpSession getSession() {
        return session;
    }
}
