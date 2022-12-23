package com.zgy.javawebclass.service;

import com.zgy.javawebclass.bean.Ask;
import com.zgy.javawebclass.bean.Question;

import java.util.List;

/**
 * @author zgy
 * @create 2022-12-18 11:09
 */
public interface QuestionService {
    List<Question> getAll();
    Boolean update(Question question);
    Boolean insert(Question question);
    Boolean remove(Integer id);
    List<Ask> getQuestions(Integer id);
    List<Ask> getQuestions(Integer studentId,Integer courseId);
    Ask getById(Integer id);
}
