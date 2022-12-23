package com.zgy.javawebclass.service;

import com.zgy.javawebclass.bean.Selection;
import com.zgy.javawebclass.bean.Student;

import java.util.List;

/**
 * @author zgy
 * @create 2022-12-18 11:09
 */
public interface SelectionService {
    List<Selection> getAll();
    Boolean update(Selection select);
    Boolean insert(Selection select);
    Boolean remove(Integer id);
    Integer insert(List<Integer> ids,Integer studentId);
    List<Integer> getCourseIds(Integer id);
    List<Student> getStudents(Integer courseId);
    Boolean updateSelectionAsk(Integer studentid,Integer courseid,Integer ask);
    Boolean updateSelectionSee(Integer studentid,Integer courseid,Integer see);
    Selection getSelection(Integer studentid,Integer courseid);
}
