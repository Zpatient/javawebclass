package com.zgy.javawebclass.dao;

import com.zgy.javawebclass.bean.Selection;

import java.util.List;

/**
 * @author zgy
 * @create 2022-12-18 11:12
 */
public interface SelectionDao {
    List<Selection> getAll();
    Integer update(Selection select);
    Integer insert(Selection select);
    Integer remove(Integer id);
    Selection getById(Integer id);
    Integer insert(List<Integer> ids,Integer studentId);
    List<Selection> getSelections(Integer studentid);
    List<Selection> getSelectionsByCourse(Integer courseid);
    Integer updateSelectionAsk(Integer studentid,Integer courseid,Integer ask);
    Integer updateSelectionSee(Integer studentid,Integer courseid,Integer see);
    Selection getSelection(Integer studentid, Integer courseid);
}
