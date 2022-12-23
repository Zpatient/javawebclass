package com.zgy.javawebclass.dao;

import com.zgy.javawebclass.bean.Question;

import java.util.List;

/**
 * @author zgy
 * @create 2022-12-18 11:12
 */
public interface QuestionDao {
    List<Question> getAll();
    Integer update(Question question);
    Integer insert(Question question);
    Integer remove(Integer id);
    Question getById(Integer id);
    List<Question> getQuestions(Integer id);
    List<Question> getQuestionsByTeacherId(Integer id);
    List<Question> getQuestionsByCourseId(Integer id);
    List<Question> getQuestions(Integer studentId,Integer courseId);
}
