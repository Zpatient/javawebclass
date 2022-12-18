package com.zgy.javawebclass.controller;
/**
 * @author zgy
 * @create 2022-12-18 10:31
 */

import com.zgy.javawebclass.bean.Course;
import com.zgy.javawebclass.service.CourseService;
import com.zgy.javawebclass.service.impl.CourseServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

@WebServlet(name = "CourseServlet", value = "/course/*")
public class CourseServlet extends HttpServlet {
    CourseService courseService = new CourseServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String target = requestURI.substring(requestURI.lastIndexOf("/")+1);
        switch (target){
            case "getall": {doGetAll(request,response);break;}
            default: {response.sendRedirect("/404.html");break;}
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String target = requestURI.substring(requestURI.lastIndexOf("/")+1);
        switch (target){
            case "insert": {doInsert(request,response);break;}
            case "update": {doUpdate(request,response);break;}
            default: {response.sendRedirect("/404.html");break;}
        }
    }

    private void doUpdate(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String s = parameterNames.nextElement();
            System.out.println(s+":"+request.getParameter(s));
        }
        List<Course> courses = courseService.getAll();
        request.setAttribute("courses",courses);
        request.getRequestDispatcher("/admin-course.jsp").forward(request,response);
    }

    private void doInsert(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String s = parameterNames.nextElement();
            System.out.println(s+":"+request.getParameter(s));
        }
        List<Course> courses = courseService.getAll();
        request.setAttribute("courses",courses);
        request.getRequestDispatcher("/admin-course.jsp").forward(request,response);
    }
    private void doGetAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Course> courses = courseService.getAll();
        request.setAttribute("courses",courses);
        request.getRequestDispatcher("/admin-course.jsp").forward(request,response);
    }
}
