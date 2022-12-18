package com.zgy.javawebclass.controller;
/**
 * @author zgy
 * @create 2022-12-18 10:31
 */

import com.zgy.javawebclass.bean.Teacher;
import com.zgy.javawebclass.service.TeacherService;
import com.zgy.javawebclass.service.impl.TeacherServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

@WebServlet(name = "TeacherServlet", value = "/teacher/*")
public class TeacherServlet extends HttpServlet {
    TeacherService teacherService = new TeacherServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String target = requestURI.substring(requestURI.lastIndexOf("/")+1);
        switch (target){
            case "getall": doGetAll(request,response);break;
            default:{response.sendRedirect("/404.html");break;}
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String target = requestURI.substring(requestURI.lastIndexOf("/")+1);
        switch (target){
            case "insert": doInsert(request,response);break;
            case "update": doUpdate(request,response);break;
            default: {response.sendRedirect("/404.html");break;}
        }
    }

    private void doUpdate(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String s = parameterNames.nextElement();
            System.out.println(s+":"+request.getParameter(s));
        }
        List<Teacher> teachers = teacherService.getAll();
        request.setAttribute("teachers",teachers);
        request.getRequestDispatcher("/admin-teacher.jsp").forward(request,response);
    }

    private void doInsert(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String s = parameterNames.nextElement();
            System.out.println(s+":"+request.getParameter(s));
        }
        List<Teacher> teachers = teacherService.getAll();
        request.setAttribute("teachers",teachers);
        request.getRequestDispatcher("/admin-teacher.jsp").forward(request,response);
    }
    private void doGetAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Teacher> teachers = teacherService.getAll();
        request.setAttribute("teachers",teachers);
        request.getRequestDispatcher("/admin-teacher.jsp").forward(request,response);
    }
}
