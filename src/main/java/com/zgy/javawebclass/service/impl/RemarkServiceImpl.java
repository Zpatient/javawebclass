package com.zgy.javawebclass.service.impl;

import com.zgy.javawebclass.bean.*;
import com.zgy.javawebclass.dao.CourseDao;
import com.zgy.javawebclass.dao.QuestionDao;
import com.zgy.javawebclass.dao.RemarkDao;
import com.zgy.javawebclass.dao.TeacherDao;
import com.zgy.javawebclass.dao.impl.CourseDaoImpl;
import com.zgy.javawebclass.dao.impl.QuestionDaoImpl;
import com.zgy.javawebclass.dao.impl.RemarkDaoImpl;
import com.zgy.javawebclass.dao.impl.TeacherDaoImpl;
import com.zgy.javawebclass.service.QuestionService;
import com.zgy.javawebclass.service.RemarkService;
import com.zgy.javawebclass.service.StudentService;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zgy
 * @create 2022-12-18 11:10
 */
public class RemarkServiceImpl implements RemarkService {
    RemarkDao remarkDao = new RemarkDaoImpl();
    QuestionDao questionDao = new QuestionDaoImpl();
    CourseDao courseDao = new CourseDaoImpl();
    TeacherDao teacherDao = new TeacherDaoImpl();
    StudentService studentService = new StudentServiceImpl();
    @Override
    public List<Remark> getAll() {
        return remarkDao.getAll();
    }

    @Override
    public Boolean update(Remark remark) {
        Integer count = remarkDao.update(remark);
        if(count.equals(1)) return true;
        else return false;
    }

    @Override
    public Boolean insert(Remark remark) {
        Integer count = remarkDao.insert(remark);
        if(count.equals(1)) return true;
        else return false;
    }

    @Override
    public Boolean remove(Integer id) {
        if(id==null) return false;
        Integer count = remarkDao.remove(id);
        if(count.equals(1)) return true;
        else return false;
    }

    @Override
    public List<Message> getMessageList(Integer id) {
        if(id == null)return null;
        List<Remark> remarks = remarkDao.getByTargetId(id);
        List<Message> messages = new ArrayList<>();
        for(Remark remark:remarks){
            Integer questionid = remark.getQuestionid();
            Question question = questionDao.getById(questionid);
            Integer courseid = question.getCourseid();
            Course course = courseDao.getById(courseid);
            Teacher teacher = teacherDao.getById(course.getTeacherid());
            if(ObjectUtils.anyNull(question,course,teacher)) return null;
            Message message = new Message();
            message.setCourse(course.getName());
            message.setQuestion(question.getQuestion());
            message.setTeacher(teacher.getName());
            message.setTime(remark.getRemarktime());
            message.setQuestionId(questionid);
            messages.add(message);
        }
        return messages;
    }

    @Override
    public List<Message> getMessagesByQuestionId(Integer questionId) {
        if(questionId == null)return null;
        List<Remark> remarks = remarkDao.getByQuestionId(questionId);
        List<Message> messages = new ArrayList<>();
        for(Remark remark:remarks){
            Integer questionid = remark.getQuestionid();
            Question question = questionDao.getById(questionid);
            Integer courseid = question.getCourseid();
            Course course = courseDao.getById(courseid);
            Teacher teacher = teacherDao.getById(course.getTeacherid());
            if(ObjectUtils.anyNull(question,course,teacher)) return null;
            Message message = new Message();
            if(teacher.getId().equals(remark.getOwnerid())){
                message.setOwnerid(teacher.getId());
                message.setOwnername(teacher.getName());
                message.setTeacher(teacher.getName());
                Student student = studentService.getById(remark.getTargetid());
                message.setStudent(student.getName());
                message.setTargetid(student.getId());
                message.setTargetname(student.getName());
            }else {
                message.setTargetid(teacher.getId());
                message.setTargetname(teacher.getName());
                message.setTeacher(teacher.getName());
                Student student = studentService.getById(remark.getOwnerid());
                message.setStudent(student.getName());
                message.setOwnerid(student.getId());
                message.setOwnername(student.getName());
            }
            message.setCourse(course.getName());
            message.setQuestion(question.getQuestion());
            message.setTime(remark.getRemarktime());
            message.setQuestionId(questionid);
            message.setContent(remark.getContent());
            messages.add(message);
        }
        return messages;
    }


}
