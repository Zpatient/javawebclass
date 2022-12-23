package com.zgy.javawebclass.service;

import com.zgy.javawebclass.bean.Message;
import com.zgy.javawebclass.bean.Remark;

import java.util.List;

/**
 * @author zgy
 * @create 2022-12-18 11:09
 */
public interface RemarkService {
    Remark getById(Integer remarkId);
    List<Remark> getAll();
    Boolean update(Remark remark);
    Boolean insert(Remark remark);
    Boolean remove(Integer id);
    List<Message> getMessageList(Integer targetid);
    List<Message> getMessagesByQuestionId(Integer questionId);
    void updateRemarkIsRead(Integer questionId,Integer userId);
}
