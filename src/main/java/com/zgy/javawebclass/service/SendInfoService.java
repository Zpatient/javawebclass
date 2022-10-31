package com.zgy.javawebclass.service;
import com.zgy.javawebclass.bean.ChatRecord;
import com.zgy.javawebclass.bean.User;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Vector;
public class SendInfoService {
    public static void saveRecord(HttpServlet servlet, HttpServletRequest request,HttpServletResponse response) throws IOException {
        //获取发送信息的当前用户
        User.UserBaseInfo currentUser =(User.UserBaseInfo)request.getSession().getAttribute("currentUser");
        if(currentUser!=null){
            ServletContext servletContext = servlet.getServletContext();
            Map<String, User> userMap =(Map<String,User>)servletContext.getAttribute("userMap");
            if(userMap!=null){
                //获取接收方用户以及信息内容
                String target = request.getParameter("target");
                String message = request.getParameter("message");
                User.UserBaseInfo targetUser = userMap.get(target).getUserBaseInfo();
                //将该条聊天记录存入Context上下文中
                ChatRecord chatRecord = new ChatRecord(currentUser, targetUser, message);
                Vector<ChatRecord> chatRecords = ( Vector<ChatRecord>)servletContext.getAttribute("chatRecords");
                chatRecords.add(chatRecord);
                response.sendRedirect("/chatroom");
            }
        }
    }
}
