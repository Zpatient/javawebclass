package com.zgy.javawebclass.dao.impl;

import com.zgy.javawebclass.bean.Remark;
import com.zgy.javawebclass.dao.RemarkDao;
import com.zgy.javawebclass.utils.DBUtil;
import static com.zgy.javawebclass.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

/**
 * @author zgy
 * @create 2022-12-18 11:12
 */
public class RemarkDaoImpl implements RemarkDao {
    public List<Remark> getAll() {
        Connection conn = DBUtil.getConn();
        String sql = "select * from remark";
        List<Remark> remarks = DBUtil.getBySql(conn, sql, Remark.class, null);
        DBUtil.close(conn);
        return remarks;
    }

    @Override
    public Integer update(Remark remark) {
        String sql = "update remark set content = ?,remarktime = ?,questionid = ?,ownerid = ?,targetid = ?,isread = ? where id = ?";
        Object[] objects = {remark.getContent(),remark.getRemarktime(),remark.getQuestionid(),remark.getOwnerid(),
                remark.getTargetid(),remark.getIsread(),remark.getId()};
        Connection conn = DBUtil.getConn();
        Integer count = DBUtil.excuteUpdate(conn, sql, objects);
        DBUtil.close(conn);
        return count;
    }

    @Override
    public Integer insert(Remark remark) {
        Connection conn = DBUtil.getConn();
        Integer count = DBUtil.save(conn, remark);
        DBUtil.close(conn);
        return count;
    }

    @Override
    public Integer remove(Integer id) {
        Connection conn = DBUtil.getConn();
        Integer count = DBUtil.removeById(conn, id, Remark.class);
        DBUtil.close(conn);
        return count;
    }

    @Override
    public List<Remark> getByTargetId(Integer targetId) {
        if(targetId==null) return null;
        Connection conn = getConn();
        String sql = "select * from remark where targetid = ?";
        List<Remark> remarks = getBySql(conn, sql, Remark.class, targetId);
        close(conn);
        return remarks;
    }

    @Override
    public List<Remark> getByQuestionId(Integer questionId) {
        if(questionId==null) return null;
        Connection conn = getConn();
        String sql = "select * from remark where questionid = ? order by remarktime asc";
        List<Remark> remarks = getBySql(conn, sql, Remark.class, questionId);
        close(conn);
        return remarks;
    }
}
