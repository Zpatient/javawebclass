package com.zgy.javawebclass.service.impl;

import com.zgy.javawebclass.bean.Selection;
import com.zgy.javawebclass.bean.Student;
import com.zgy.javawebclass.dao.SelectionDao;
import com.zgy.javawebclass.dao.StudentDao;
import com.zgy.javawebclass.dao.impl.SelectionDaoImpl;
import com.zgy.javawebclass.dao.impl.StudentDaoImpl;
import com.zgy.javawebclass.service.SelectionService;
import com.zgy.javawebclass.service.StudentService;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zgy
 * @create 2022-12-18 11:10
 */
public class SelectionServiceImpl implements SelectionService {
    SelectionDao selectionDao = new SelectionDaoImpl();
    StudentDao studentDao = new StudentDaoImpl();
    @Override
    public List<Selection> getAll() {
        return selectionDao.getAll();
    }

    @Override
    public Boolean update(Selection selection) {
        Integer count = selectionDao.update(selection);
        if(count.equals(1)) return true;
        else return false;
    }

    @Override
    public Boolean insert(Selection selection) {
        Integer count = selectionDao.insert(selection);
        if(count.equals(1)) return true;
        else return false;
    }

    @Override
    public Boolean remove(Integer id) {
        if(id==null) return false;
        Integer count = selectionDao.remove(id);
        if(count.equals(1)) return true;
        else return false;
    }

    @Override
    public Integer insert(List<Integer> ids, Integer studentId) {
        if(ids==null)return 0;
        Integer count = selectionDao.insert(ids, studentId);
        return count;
    }

    @Override
    public List<Integer> getCourseIds(Integer id) {
        if(id==null)return null;
        List<Integer> ids = new ArrayList<>();
        List<Selection> selections = selectionDao.getSelections(id);
        for(Selection selection:selections){
            ids.add(selection.getCourseid());
        }
        return ids;
    }

    @Override
    public List<Student> getStudents(Integer courseId) {
        if(courseId==null)return null;
        ArrayList<Student> students = new ArrayList<>();
        List<Selection> selections = selectionDao.getSelectionsByCourse(courseId);
        for(Selection selection:selections){
            Integer studentid = selection.getStudentid();
            Student student = studentDao.getById(studentid);
            students.add(student);
        }
        return students;
    }

    @Override
    public Boolean updateSelectionAsk(Integer studentid, Integer courseid, Integer ask) {
        if(ObjectUtils.anyNull(studentid,courseid,ask))return false;
        Integer count = selectionDao.updateSelectionAsk(studentid, courseid, ask);
        if(count.equals(1)) return true;
        else return false;
    }

    @Override
    public Boolean updateSelectionSee(Integer studentid, Integer courseid, Integer see) {
        if(ObjectUtils.anyNull(studentid,courseid,see))return false;
        Integer count = selectionDao.updateSelectionSee(studentid, courseid, see);
        if(count.equals(1)) return true;
        else return false;
    }

    @Override
    public Selection getSelection(Integer studentid, Integer courseid) {
        if(ObjectUtils.anyNull(studentid,courseid)) return null;
        Selection selection = selectionDao.getSelection(studentid, courseid);
        return selection;
    }
}
