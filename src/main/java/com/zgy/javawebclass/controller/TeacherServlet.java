package com.zgy.javawebclass.controller;
/**
 * @author zgy
 * @create 2022-12-18 10:31
 */

import com.zgy.javawebclass.bean.*;
import com.zgy.javawebclass.service.*;
import com.zgy.javawebclass.service.impl.*;
import com.zgy.javawebclass.utils.ParamsToBean;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "TeacherServlet", value = "/teacher/*")
public class TeacherServlet extends HttpServlet {
    TeacherService teacherService = new TeacherServiceImpl();
    CourseService courseService = new CourseServiceImpl();
    RemarkService remarkService = new RemarkServiceImpl();
    QuestionService questionService = new QuestionServiceImpl();
    SelectionService selectionService = new SelectionServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String target = requestURI.substring(requestURI.lastIndexOf("/")+1);
        switch (target){
            case "getall": doGetAll(request,response);break;
            case "remove": doRemove(request,response);break;
            case "login": getLogin(request,response);break;
            case "getquestion": doGetQuestion(request,response);break;
            case "getcourse": doGetCourse(request,response);break;
            case "updateSelectionAsk": doUpdateSelectionAsk(request,response);break;
            case "updateSelectionSee": doUpdateSelectionSee(request,response);break;
            default:{response.sendRedirect("/404.html");break;}
        }
    }

    private void doUpdateSelectionAsk(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String studentIdStr = request.getParameter("studentId");
        String courseIdStr = request.getParameter("courseId");
        String askStr = request.getParameter("ask");
        if(StringUtils.isAnyBlank(studentIdStr,courseIdStr,askStr))response.sendRedirect("/404.html");
        Integer studentId = Integer.parseInt(studentIdStr);
        Integer courseId = Integer.parseInt(courseIdStr);
        Integer ask = Integer.parseInt(askStr);
        Boolean result = selectionService.updateSelectionAsk(studentId, courseId, ask);
    }

    private void doUpdateSelectionSee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String studentIdStr = request.getParameter("studentId");
        String courseIdStr = request.getParameter("courseId");
        String seeStr = request.getParameter("see");
        if(StringUtils.isAnyBlank(studentIdStr,courseIdStr,seeStr))response.sendRedirect("/404.html");
        Integer studentId = Integer.parseInt(studentIdStr);
        Integer courseId = Integer.parseInt(courseIdStr);
        Integer see = Integer.parseInt(seeStr);
        Boolean result = selectionService.updateSelectionSee(studentId, courseId, see);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String target = requestURI.substring(requestURI.lastIndexOf("/")+1);
        switch (target){
            case "insert": doInsert(request,response);break;
            case "update": doUpdate(request,response);break;
            case "login": doLogin(request,response);break;
            case "changepassword": doChangePassword(request,response);break;
            default: {response.sendRedirect("/404.html");break;}
        }
    }
    private void doGetQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Teacher user = (Teacher)request.getSession().getAttribute("user");
        if(user==null) response.sendRedirect("/teacher/login");
        List<Message> messages = remarkService.getMessageList(user.getId());
        request.setAttribute("messages",messages);
        request.setAttribute("count",messages.size());
        List<Ask> asks = questionService.getQuestionsByTeacherId(user.getId());
        request.setAttribute("asks",asks);
        request.getRequestDispatcher("/teacher-message.jsp").forward(request,response);
    }
    private void doGetCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Teacher user = (Teacher)request.getSession().getAttribute("user");
        if(user==null) response.sendRedirect("/teacher/login");
        List<CourseView> courseViews = teacherService.getCourses(user.getId());
        request.setAttribute("courseViews",courseViews);
        List<Message> messages = remarkService.getMessageList(user.getId());
        request.setAttribute("count",messages.size());
        request.getRequestDispatcher("/teacher-course.jsp").forward(request,response);
    }
    private void getLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/teacherlogin.jsp").forward(request,response);
    }
    private void doChangePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String password= request.getParameter("password");
        String confirmPassword = request.getParameter("confirmpassword");
        if(StringUtils.isAnyBlank(password,confirmPassword)||!password.equals(confirmPassword))
            response.sendRedirect("/404.html");
        else{
            Teacher user =(Teacher) request.getSession().getAttribute("user");
            Boolean result = teacherService.teacherChangePassword(user, password);
            if(result) response.sendRedirect("/teacher/login");
            else response.sendRedirect("/404.html");
        }
    }
    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        if(StringUtils.isAnyBlank(id,password))
            response.sendRedirect("/404.html");
        else{
            Integer ID = Integer.parseInt(id);
            Teacher teacher = teacherService.teacherLogin(ID, password);
            if(teacher!=null) {
                HttpSession session = request.getSession();
                session.setAttribute("type","teacher");
                session.setAttribute("user",teacher);
                List<Course> courses = courseService.getAll();
                session.setAttribute("courses",courses);
                response.sendRedirect("/teacher/getquestion");
            }
            else
                response.sendRedirect("/teacher/login");
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
