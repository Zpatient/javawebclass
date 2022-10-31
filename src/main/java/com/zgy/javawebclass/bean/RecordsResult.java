package com.zgy.javawebclass.bean;
import java.io.Serializable;
import java.util.Vector;
public class RecordsResult implements Serializable {
    User.UserBaseInfo currentUser;
    Vector<ChatRecord> chatRecords;

    public RecordsResult(User.UserBaseInfo currentUser, Vector<ChatRecord> chatRecords) {
        this.currentUser = currentUser;
        this.chatRecords = chatRecords;
    }
}
