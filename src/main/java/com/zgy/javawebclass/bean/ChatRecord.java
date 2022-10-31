package com.zgy.javawebclass.bean;
public class ChatRecord {
    User.UserBaseInfo sender;
    User.UserBaseInfo target;
    String message;
    public ChatRecord(User.UserBaseInfo sender, User.UserBaseInfo target, String message) {
        this.sender = sender;
        this.target = target;
        this.message = message;
    }
    public User.UserBaseInfo getSender() {
        return sender;
    }
    public User.UserBaseInfo getTarget() {
        return target;
    }
}
