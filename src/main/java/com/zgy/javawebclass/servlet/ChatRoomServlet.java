package com.zgy.javawebclass.servlet;
import com.zgy.javawebclass.service.ChatRoomService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ChatRoomServlet", value = "/chatroom")
public class ChatRoomServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用ChatRoomService的sendResponse方法返回响应结果给前端
        ChatRoomService.sendResponse(this,request,response);
    }
}
