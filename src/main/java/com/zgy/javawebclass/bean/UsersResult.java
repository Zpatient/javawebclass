package com.zgy.javawebclass.bean;
import java.io.Serializable;
import java.util.Vector;
public class UsersResult implements Serializable {
    User.UserBaseInfo currentUser;
    Vector<User.UserBaseInfo> usersVector;

    public UsersResult(User.UserBaseInfo currentUser, Vector<User.UserBaseInfo> usersVector) {
        this.currentUser = currentUser;
        this.usersVector = usersVector;
    }
}
