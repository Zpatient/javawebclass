package com.zgy.javawebclass.controller; /**
 * @author zgy
 * @create 2022-12-24 4:24
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LogOutServlet", value = "/logout")
public class LogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session!=null){
            String type =(String)session.getAttribute("type");session.invalidate();
            if(type.equals("student")) response.sendRedirect("/student/login");
            else if(type.equals("teacher")) response.sendRedirect("/teacher/login");
            else if(type.equals("admin")) response.sendRedirect("/admin/login");
            else response.sendRedirect("/student/login");
        }
    }
}
