package com.zgy.javawebclass.service;

import com.google.gson.Gson;
import com.zgy.javawebclass.bean.ChatRecord;
import com.zgy.javawebclass.bean.RecordsResult;
import com.zgy.javawebclass.bean.User;
import com.zgy.javawebclass.bean.UsersResult;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
public class ChatRoomService {

    public static void sendResponse(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        //若session域中未找到currentUser信息，说明用户并未登录,跳转到登录界面
        User.UserBaseInfo currentUser =(User.UserBaseInfo)session.getAttribute("currentUser");
        if(currentUser == null){
            response.sendRedirect("/login");
        }
        else {
            String from = request.getParameter("from");
            if(from!=null){
                ServletContext servletContext = servlet.getServletContext();
                if(from.equals("chatroom")){
                    //从上下文环境中获取聊天记录
                    Vector<ChatRecord> chatRecords =(Vector<ChatRecord>) servletContext.getAttribute("chatRecords");
                    if(chatRecords!=null){
                        Vector<ChatRecord> clone =(Vector<ChatRecord>)chatRecords.clone();
                        for (int i = 0; i < clone.size(); i++) {
                            ChatRecord chatRecord = clone.elementAt(i);
                            String targetname = chatRecord.getTarget().getName();
                            String sendername = chatRecord.getSender().getName();
                            String currentname = currentUser.getName();
                            if(!sendername.equals(currentname)&&!targetname.equals(currentname)&&!targetname.equals("所有人"))
                                clone.remove(i);
                        }
                        //将结果转换成Json字符串发送给前端
                        RecordsResult recordsResult = new RecordsResult(currentUser, clone);
                        String resultJson = new Gson().toJson(recordsResult);
                        response.setContentType("application/json;charset=UTF-8");
                        response.getWriter().print(resultJson);
                    }
                }
                else if(from.equals("select")){
                    Map<String,User> userMap = (Map<String,User>)servletContext.getAttribute("userMap");
                    if(userMap!=null){
                        Set<String> names = userMap.keySet();
                        Vector<User.UserBaseInfo> usersVector = new Vector<>();
                        for (String key : names){
                            if(key.equals("所有人"))
                                continue;
                            User user = userMap.get(key);
                            HttpSession userSession = user.getSession();
                            userSession.setMaxInactiveInterval(900);
                            long currentTime = new Date().getTime();
                            int maxInactiveInterval = userSession.getMaxInactiveInterval();
                            long lastAccessedTime = userSession.getLastAccessedTime();
                            //若当前时间减去上次访问时间超过了会话有效时间，说明该用户不在线，移除该用户
                            if((currentTime-lastAccessedTime)>maxInactiveInterval*1000){
                                userMap.remove(key);
                            }
                            else
                                usersVector.add(user.getUserBaseInfo());
                        }
                        //将当前在线的用户信息转化为Json返回给前端
                        UsersResult usersResult = new UsersResult(currentUser, usersVector);
                        String usersResultJson = new Gson().toJson(usersResult);
                        response.setContentType("application/json;charset=UTF-8");
                        response.getWriter().print(usersResultJson);
                    }
                }
                else
                    printChatRoomHtml(response);
            }
            else
                printChatRoomHtml(response);
        }
    }
    public static void printChatRoomHtml(HttpServletResponse response) throws IOException {
        response.sendRedirect("/chatroom.html");
    }
}
