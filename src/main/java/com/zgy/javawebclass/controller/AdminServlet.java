package com.zgy.javawebclass.controller; /**
 * @author zgy
 * @create 2022-12-18 17:03
 */

import com.zgy.javawebclass.bean.Admin;
import com.zgy.javawebclass.service.AdminService;
import com.zgy.javawebclass.service.impl.AdminServiceImpl;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdminServlet", value = "/admin/*")
public class AdminServlet extends HttpServlet {
    AdminService adminService = new AdminServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String target = requestURI.substring(requestURI.lastIndexOf("/")+1);
        switch (target){
            case "login": getLogin(request,response);break;
            default:{response.sendRedirect("/404.html");break;}
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String target = requestURI.substring(requestURI.lastIndexOf("/")+1);
        switch (target){
            case "login": doLogin(request,response);break;
            case "changepassword": doChangePassword(request,response);break;
            default: {response.sendRedirect("/404.html");break;}
        }
    }

    private void doChangePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String password= request.getParameter("password");
        String confirmPassword = request.getParameter("confirmpassword");
        if(StringUtils.isAnyBlank(password,confirmPassword)||!password.equals(confirmPassword))
            response.sendRedirect("/404.html");
        else{
            Admin user =(Admin) request.getSession().getAttribute("user");
            Boolean result = adminService.adminChangePassword(user, password);
            if(result) response.sendRedirect("/admin/login");
            else response.sendRedirect("/404.html");
        }
    }

    private void getLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/adminlogin.jsp").forward(request,response);
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        if(StringUtils.isAnyBlank(id,password))
            response.sendRedirect("/404.html");
        else{
            Integer ID = Integer.parseInt(id);
            Admin admin = adminService.adminLogin(ID, password);
            if(admin!=null) {
                HttpSession session = request.getSession();
                session.setAttribute("type","admin");
                session.setAttribute("user",admin);
                response.sendRedirect("/course/getall");
            }
            else
                response.sendRedirect("/admin/login");
        }
    }
}
