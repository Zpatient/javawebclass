package com.zgy.javawebclass.service.impl;

import com.zgy.javawebclass.bean.*;
import com.zgy.javawebclass.dao.CourseDao;
import com.zgy.javawebclass.dao.QuestionDao;
import com.zgy.javawebclass.dao.StudentDao;
import com.zgy.javawebclass.dao.TeacherDao;
import com.zgy.javawebclass.dao.impl.CourseDaoImpl;
import com.zgy.javawebclass.dao.impl.QuestionDaoImpl;
import com.zgy.javawebclass.dao.impl.StudentDaoImpl;
import com.zgy.javawebclass.dao.impl.TeacherDaoImpl;
import com.zgy.javawebclass.service.QuestionService;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zgy
 * @create 2022-12-18 11:10
 */
public class QuestionServiceImpl implements QuestionService {
    QuestionDao questionDao = new QuestionDaoImpl();
    StudentDao studentDao = new StudentDaoImpl();
    CourseDao courseDao = new CourseDaoImpl();
    TeacherDao teacherDao =new TeacherDaoImpl();
    @Override
    public List<Question> getAll() {
        return questionDao.getAll();
    }

    @Override
    public Boolean update(Question question) {
        Integer count = questionDao.update(question);
        if(count.equals(1)) return true;
        else return false;
    }
    @Override
    public Boolean insert(Question question) {
        Integer count = questionDao.insert(question);
        if(count.equals(1)) return true;
        else return false;
    }
    @Override
    public Boolean remove(Integer id) {
        if(id==null) return false;
        Integer count = questionDao.remove(id);
        if(count.equals(1)) return true;
        else return false;
    }
    @Override
    public List<Ask> getQuestions(Integer id) {
        if(id==null)return null;
        List<Question> questions = questionDao.getQuestions(id);
        List<Ask> asks = new ArrayList<>();
        for(Question question:questions){
            String content = question.getQuestion();
            Course course = courseDao.getById(question.getCourseid());
            Student student = studentDao.getById(question.getStudentid());
            if(ObjectUtils.anyNull(student,course)) return null;
            Teacher teacher = teacherDao.getById(course.getTeacherid());
            if(teacher==null) return null;
            Ask ask = new Ask();
            ask.setQuestionId(question.getId());
            ask.setQuestion(content);
            ask.setCourse(course.getName());
            ask.setStudent(student.getName());
            ask.setTeacher(teacher.getName());
            ask.setStudentId(student.getId());
            asks.add(ask);
        }
        return asks;
    }

    @Override
    public List<Ask> getQuestions(Integer studentId,Integer courseId) {
        if(ObjectUtils.anyNull(studentId,courseId)) return null;
        List<Question> questions = questionDao.getQuestions(studentId,courseId);
        List<Ask> asks = new ArrayList<>();
        for(Question question:questions){
            String content = question.getQuestion();
            Course course = courseDao.getById(question.getCourseid());
            Student student = studentDao.getById(question.getStudentid());
            if(ObjectUtils.anyNull(student,course)) return null;
            Teacher teacher = teacherDao.getById(course.getTeacherid());
            if(teacher==null) return null;
            Ask ask = new Ask();
            ask.setQuestionId(question.getId());
            ask.setQuestion(content);
            ask.setCourse(course.getName());
            ask.setStudent(student.getName());
            ask.setTeacher(teacher.getName());
            ask.setAskTime(question.getAsktime());
            ask.setStudentId(question.getStudentid());
            Integer questionStudentid = question.getStudentid();
            if(studentId.equals(questionStudentid))
                ask.setShow(true);
            else
                ask.setShow(false);
            asks.add(ask);
        }
        return asks;
    }

    @Override
    public Ask getById(Integer id) {
        if(id == null)return null;
        Question question = questionDao.getById(id);
        String content = question.getQuestion();
        Course course = courseDao.getById(question.getCourseid());
        Student student = studentDao.getById(question.getStudentid());
        if(ObjectUtils.anyNull(student,course)) return null;
        Teacher teacher = teacherDao.getById(course.getTeacherid());
        if(teacher==null) return null;
        Ask ask = new Ask();
        ask.setQuestionId(question.getId());
        ask.setQuestion(content);
        ask.setCourse(course.getName());
        ask.setStudent(student.getName());
        ask.setTeacher(teacher.getName());
        ask.setAskTime(question.getAsktime());
        ask.setStudentId(question.getStudentid());
        ask.setTeacherId(teacher.getId());
        return ask;
    }
}
