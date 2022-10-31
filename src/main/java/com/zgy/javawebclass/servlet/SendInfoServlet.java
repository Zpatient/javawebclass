package com.zgy.javawebclass.servlet;
import com.zgy.javawebclass.service.SendInfoService;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SendInfoServlet", value = "/sendinfo")
public class SendInfoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符编码
        request.setCharacterEncoding("utf-8");
        //调用SendInfoService的saveRecord方法将聊天记录保存到context上下文中
        SendInfoService.saveRecord(this,request,response);
    }
}
