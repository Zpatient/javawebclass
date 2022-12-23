package com.zgy.javawebclass.controller;
/**
 * @author zgy
 * @create 2022-12-18 10:31
 */

import com.zgy.javawebclass.bean.Teacher;
import com.zgy.javawebclass.service.TeacherService;
import com.zgy.javawebclass.service.impl.TeacherServiceImpl;
import com.zgy.javawebclass.utils.ParamsToBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "TeacherServlet", value = "/teacher/*")
public class TeacherServlet extends HttpServlet {
    TeacherService teacherService = new TeacherServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String target = requestURI.substring(requestURI.lastIndexOf("/")+1);
        switch (target){
            case "getall": doGetAll(request,response);break;
            case "remove": doRemove(request,response);break;
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
    private void doRemove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        PrintWriter writer = response.getWriter();
        String id = request.getParameter("id");
        if(id==null) writer.print("false");
        Boolean result = teacherService.remove(Integer.parseInt(id));
        if(result) writer.print("true");
        else writer.print("false");
        writer.close();
    }
    private void doUpdate(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        Teacher teacher = ParamsToBean.transform(map, Teacher.class);
        Boolean result = teacherService.update(teacher);
        if(result){
            List<Teacher> teachers = teacherService.getAll();
            request.setAttribute("teachers",teachers);
            request.getRequestDispatcher("/admin-teacher.jsp").forward(request,response);
        }else {
            response.sendRedirect("/404.html");
        }
    }
    private void doInsert(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        Teacher teacher = ParamsToBean.transform(map, Teacher.class);
        Boolean result = teacherService.insert(teacher);
        if(result){
            List<Teacher> teachers = teacherService.getAll();
            request.setAttribute("teachers",teachers);
            request.getRequestDispatcher("/admin-teacher.jsp").forward(request,response);
        }else {
            response.sendRedirect("/404.html");
        }
    }
    private void doGetAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Teacher> teachers = teacherService.getAll();
        request.setAttribute("teachers",teachers);
        request.getRequestDispatcher("/admin-teacher.jsp").forward(request,response);
    }
}
