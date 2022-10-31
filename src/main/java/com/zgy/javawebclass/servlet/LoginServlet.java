package com.zgy.javawebclass.servlet;
import com.zgy.javawebclass.bean.ChatRecord;
import com.zgy.javawebclass.bean.User;
import com.zgy.javawebclass.service.LoginService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext servletContext = getServletContext();
        //将用户账号信息保存到Context上下文中
        Map<String,User> userMap = new HashMap<>();
        userMap.put("所有人",new User(new User.UserBaseInfo("所有人","未知"),null));
        servletContext.setAttribute("userMap",userMap);
        //创建一个聊天记录表保存到Context上下文中
        Vector<ChatRecord> chatRecords = new Vector<>();
        servletContext.setAttribute("chatRecords",chatRecords);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符编码
        request.setCharacterEncoding("utf-8");
        //调用LoginService的userlogin方法执行用户登录业务
        LoginService.userlogin(this, request, response);
    }
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //调用LoginService的printLoginHtml方法返回给用户登录页面
        LoginService.printLoginHtml(response);
    }
}
