package com.zgy.javawebclass.controller;
/**
 * @author zgy
 * @create 2022-12-18 10:31
 */

import com.zgy.javawebclass.bean.Ask;
import com.zgy.javawebclass.bean.Course;
import com.zgy.javawebclass.bean.CourseView;
import com.zgy.javawebclass.bean.Student;
import com.zgy.javawebclass.service.CourseService;
import com.zgy.javawebclass.service.QuestionService;
import com.zgy.javawebclass.service.SelectionService;
import com.zgy.javawebclass.service.impl.CourseServiceImpl;
import com.zgy.javawebclass.service.impl.QuestionServiceImpl;
import com.zgy.javawebclass.service.impl.SelectionServiceImpl;
import com.zgy.javawebclass.utils.ParamsToBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CourseServlet", value = "/course/*")
public class CourseServlet extends HttpServlet {
    CourseService courseService = new CourseServiceImpl();
    QuestionService questionService = new QuestionServiceImpl();
    SelectionService selectionService = new SelectionServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String target = requestURI.substring(requestURI.lastIndexOf("/")+1);
        switch (target){
            case "getall": {doGetAll(request,response);break;}
            case "remove": doRemove(request,response);break;
            case "getdetail": doGetDetail(request,response);break;
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

    private void doGetDetail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        if(id == null ) response.sendRedirect("/404.html");
        ArrayList<Integer> list = new ArrayList<>();list.add(Integer.parseInt(id));
        List<CourseView> courseViews = courseService.getCourseViews(list);
        if(courseViews.size()!=1) response.sendRedirect("/404.html");
        CourseView courseView = courseViews.get(0);
        //课程信息
        request.setAttribute("courseView",courseView);
        String type = (String) request.getSession().getAttribute("type");
        if(type.equals("student")) {
            Student user = (Student)request.getSession().getAttribute("user");
            if(user==null) response.sendRedirect("/student/login");
            //提问列表
            List<Ask> asks = questionService.getQuestions(user.getId(),courseView.getId());
            request.setAttribute("asks",asks);
            //学生列表
            List<Student> students = selectionService.getStudents(courseView.getId());
            request.setAttribute("students",students);
            request.getRequestDispatcher("/student-course-detail.jsp").forward(request,response);
        }else if(type.equals("teacher")){



            
        }else response.sendRedirect("/404.html");
    }
    private void doRemove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        PrintWriter writer = response.getWriter();
        String id = request.getParameter("id");
        if(id==null) writer.print("false");
        Boolean result = courseService.remove(Integer.parseInt(id));
        if(result) writer.print("true");
        else writer.print("false");
        writer.close();
    }
    private void doUpdate(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        Course course = ParamsToBean.transform(map, Course.class);
        Boolean result = courseService.update(course);
        if(result){
            List<Course> courses = courseService.getAll();
            request.setAttribute("courses",courses);
            request.getRequestDispatcher("/admin-course.jsp").forward(request,response);
        }else {
            response.sendRedirect("/404.html");
        }
    }

    private void doInsert(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        Course course = ParamsToBean.transform(map, Course.class);
        Boolean result = courseService.insert(course);
        if(result){
            List<Course> courses = courseService.getAll();
            request.setAttribute("courses",courses);
            request.getRequestDispatcher("/admin-course.jsp").forward(request,response);
        }else {
            response.sendRedirect("/404.html");
        }
    }
    private void doGetAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Course> courses = courseService.getAll();
        request.setAttribute("courses",courses);
        request.getRequestDispatcher("/admin-course.jsp").forward(request,response);
    }
}
