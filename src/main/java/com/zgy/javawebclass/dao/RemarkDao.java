package com.zgy.javawebclass.dao;

import com.zgy.javawebclass.bean.Remark;

import java.util.List;

/**
 * @author zgy
 * @create 2022-12-18 11:12
 */
public interface RemarkDao {
    List<Remark> getAll();
    Integer update(Remark remark);
    Integer insert(Remark remark);
    Integer remove(Integer id);
    List<Remark> getByTargetId(Integer targetId);
    List<Remark> getByQuestionId(Integer questionId);
    void updateRemarkIsRead(Integer questionId,Integer userId);
    Remark getById(Integer remarkId);
}
