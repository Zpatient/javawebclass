package com.zgy.javawebclass.service;
import com.zgy.javawebclass.bean.User;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
public class LoginService {

    public static void userlogin(HttpServlet servlet,HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        response.setCharacterEncoding("utf-8");
        response.setHeader("content-type","text/html;charset=utf-8");
        ServletContext servletContext = servlet.getServletContext();
        //获取context上下文中的用户表,并将该用户写入用户表
        Map<String, User> userMap =(Map<String,User>)servletContext.getAttribute("userMap");
        if (userMap!=null) {
            HttpSession session = request.getSession();
            User currentUser = new User(new User.UserBaseInfo(name, sex), session);
            userMap.put(name, currentUser);
            //将当前用户信息保存到session域中,便于后续登录判断
            session.setAttribute("currentUser", currentUser.getUserBaseInfo());
            response.sendRedirect("/chatroom?from=login");
        }
    }
    public static void printLoginHtml(HttpServletResponse response) throws IOException {
        response.sendRedirect("/login.html");
    }
}
