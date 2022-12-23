package com.zgy.javawebclass.dao.impl;

import com.zgy.javawebclass.bean.Selection;
import com.zgy.javawebclass.dao.SelectionDao;
import com.zgy.javawebclass.utils.DBUtil;

import static com.zgy.javawebclass.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

/**
 * @author zgy
 * @create 2022-12-18 11:12
 */
public class SelectionDaoImpl implements SelectionDao {
    public List<Selection> getAll() {
        Connection conn = DBUtil.getConn();
        String sql = "select * from select";
        List<Selection> selects = DBUtil.getBySql(conn, sql, Selection.class, null);
        DBUtil.close(conn);
        return selects;
    }

    @Override
    public Integer update(Selection select) {
        String sql = "update select set studentid = ?,courseid = ? where id = ?";
        Object[] objects = {select.getStudentid(),select.getCourseid(),select.getId()};
        Connection conn = DBUtil.getConn();
        Integer count = DBUtil.excuteUpdate(conn, sql, objects);
        DBUtil.close(conn);
        return count;
    }

    @Override
    public Integer insert(Selection select) {
        Connection conn = DBUtil.getConn();
        Integer count = DBUtil.save(conn, select);
        DBUtil.close(conn);
        return count;
    }

    @Override
    public Integer remove(Integer id) {
        Connection conn = DBUtil.getConn();
        Integer count = DBUtil.removeById(conn, id, Selection.class);
        DBUtil.close(conn);
        return count;
    }

    @Override
    public Selection getById(Integer id) {
        Connection conn = DBUtil.getConn();
        Selection select = DBUtil.getById(conn, id, Selection.class);
        DBUtil.close(conn);
        return select;
    }

    @Override
    public Integer insert(List<Integer> ids, Integer studentId) {

        int count = 0;
        Connection conn = getConn();
        for(Integer courseId:ids){
            String sql ="insert into selection values(null,?,?,1,1);";
            Object[] objects = {studentId,courseId};
            Integer update = excuteUpdate(conn, sql, objects);
            count+=update;
        }
        close(conn);
        return count;
    }

    @Override
    public List<Selection> getSelections(Integer id) {
        String sql = "select * from selection where studentid = "+id;
        Connection conn = getConn();
        List<Selection> selections = getBySql(conn, sql, Selection.class, null);
        return selections;
    }
    @Override
    public List<Selection> getSelectionsByCourse(Integer id) {
        String sql = "select * from selection where courseid = "+id;
        Connection conn = getConn();
        List<Selection> selections = getBySql(conn, sql, Selection.class, null);
        return selections;
    }

    @Override
    public Integer updateSelectionAsk(Integer studentid, Integer courseid, Integer ask) {
        String sql = "update selection set ask = ? where studentid = ? and courseid = ?";
        Object[] objects = {ask,studentid,courseid};
        Connection conn = getConn();
        Integer count = excuteUpdate(conn, sql, objects);
        return count;
    }

    @Override
    public Integer updateSelectionSee(Integer studentid, Integer courseid, Integer see) {
        String sql = "update selection set see = ? where studentid = ? and courseid = ?";
        Object[] objects = {see,studentid,courseid};
        Connection conn = getConn();
        Integer count = excuteUpdate(conn, sql, objects);
        return count;
    }

    @Override
    public Selection getSelection(Integer studentid, Integer courseid) {
        String sql = "select * from selection where studentid = ? and courseid = ?";
        Object[] objects = {studentid,courseid};
        Connection conn = getConn();
        List<Selection> selections = getBySql(conn, sql, Selection.class, objects);
        if(selections.size()!=1) return null;
        else{
            Selection selection = selections.get(0);
            return selection;
        }
    }

}
