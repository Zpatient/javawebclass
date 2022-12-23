package com.zgy.javawebclass.dao.impl;

import com.zgy.javawebclass.bean.Question;
import com.zgy.javawebclass.bean.Question;
import com.zgy.javawebclass.dao.QuestionDao;
import com.zgy.javawebclass.utils.DBUtil;
import static com.zgy.javawebclass.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

/**
 * @author zgy
 * @create 2022-12-18 11:12
 */
public class QuestionDaoImpl implements QuestionDao {
    public List<Question> getAll() {
        Connection conn = DBUtil.getConn();
        String sql = "select * from question";
        List<Question> questions = DBUtil.getBySql(conn, sql, Question.class, null);
        DBUtil.close(conn);
        return questions;
    }

    @Override
    public Integer update(Question question) {
        String sql = "update question set question = ?,askTime = ?,courseId = ?,studentId = ?，see = ? where id = ?";
        Object[] objects = {question.getQuestion(),question.getAsktime(),question.getCourseid(),question.getStudentid(),
                question.getSee(),question.getId()};
        Connection conn = DBUtil.getConn();
        Integer count = DBUtil.excuteUpdate(conn, sql, objects);
        DBUtil.close(conn);
        return count;
    }

    @Override
    public Integer insert(Question question) {
        Connection conn = DBUtil.getConn();
        Integer count = DBUtil.save(conn, question);
        DBUtil.close(conn);
        return count;
    }

    @Override
    public Integer remove(Integer id) {
        Connection conn = DBUtil.getConn();
        Integer count = DBUtil.removeById(conn, id, Question.class);
        DBUtil.close(conn);
        return count;
    }
    @Override
    public Question getById(Integer id) {
        Connection conn = DBUtil.getConn();
        Question question = DBUtil.getById(conn, id, Question.class);
        DBUtil.close(conn);
        return question;
    }

    @Override
    public List<Question> getQuestions(Integer id) {
        String sql = "select * from question where studentId = "+id+" or see = 1";
        Connection conn = getConn();
        List<Question> questions = getBySql(conn, sql, Question.class, null);
        close(conn);
        return questions;
    }
    @Override
    public List<Question> getQuestions(Integer studentId,Integer courseId) {
        String sql = "select * from question where courseId = "+courseId+" and (see = 1 or studentId = "+studentId+")";
        Connection conn = getConn();
        List<Question> questions = getBySql(conn, sql, Question.class, null);
        close(conn);
        return questions;
    }
}
