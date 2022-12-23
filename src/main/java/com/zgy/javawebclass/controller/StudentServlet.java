package com.zgy.javawebclass.controller;
/**
 * @author zgy
 * @create 2022-12-18 10:31
 */

import com.zgy.javawebclass.bean.*;
import com.zgy.javawebclass.dao.SelectionDao;
import com.zgy.javawebclass.dao.impl.SelectionDaoImpl;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "StudentServlet", value = "/student/*")
public class StudentServlet extends HttpServlet {
    StudentService studentService = new StudentServiceImpl();
    RemarkService remarkService = new RemarkServiceImpl();
    QuestionService questionService = new QuestionServiceImpl();
    SelectionService selectionService = new SelectionServiceImpl();
    CourseService courseService = new CourseServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String target = requestURI.substring(requestURI.lastIndexOf("/")+1);
        switch (target){
            case "getquestion": doGetQuestion(request,response);break;
            case "getcourse": doGetCourse(request,response);break;
            case "login": getLogin(request,response);break;
            case "register":getRegister(request,response);
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
            case "register":doRegister(request,response);break;
            case "select": doSelect(request,response);break;
            default: {response.sendRedirect("/404.html");break;}
        }
    }

    private void doRegister(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Map<String, String[]> map = request.getParameterMap();
        StudentRegister studentRegister = ParamsToBean.transform(map, StudentRegister.class);
        Boolean result = studentService.register(studentRegister);
        if(!result) response.sendRedirect("/404.html");
        request.getRequestDispatcher("/student/login").forward(request,response);
    }
    private void getLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/studentlogin.jsp").forward(request,response);
    }
    private void doGetQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student user = (Student)request.getSession().getAttribute("user");
        if(user==null) response.sendRedirect("/student/login");
        List<Message> messages = remarkService.getMessageList(user.getId());
        request.setAttribute("messages",messages);
        request.setAttribute("count",messages.size());
        List<Ask> asks = questionService.getQuestions(user.getId());
        request.setAttribute("asks",asks);
        request.getRequestDispatcher("/student-message.jsp").forward(request,response);
    }
    private void doGetCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student user = (Student)request.getSession().getAttribute("user");
        if(user==null) response.sendRedirect("/student/login");
        List<CourseView> courseViews = studentService.getCourses(user.getId());
        request.setAttribute("courseViews",courseViews);
        List<Message> messages = remarkService.getMessageList(user.getId());
        request.setAttribute("count",messages.size());
        request.getRequestDispatcher("/student-course.jsp").forward(request,response);
    }
    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        if(StringUtils.isAnyBlank(id,password))
            response.sendRedirect("/404.html");
        else{
            Integer ID = Integer.parseInt(id);
            Student student = studentService.studentLogin(ID, password);
            if(student!=null) {
                HttpSession session = request.getSession();
                session.setAttribute("type","student");
                session.setAttribute("user",student);
                List<Course> courses = courseService.getAll();
                session.setAttribute("courses",courses);
                response.sendRedirect("/student/getquestion");
            }
            else
                response.sendRedirect("/student/login");
        }
    }
    private void doChangePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String password= request.getParameter("password");
        String confirmPassword = request.getParameter("confirmpassword");
        if(StringUtils.isAnyBlank(password,confirmPassword)||!password.equals(confirmPassword))
            response.sendRedirect("/404.html");
        else{
            Student user =(Student) request.getSession().getAttribute("user");
            Boolean result = studentService.studentChangePassword(user, password);
            if(result) response.sendRedirect("/student/login");
            else response.sendRedirect("/404.html");
        }
    }
    private void doSelect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] selects = request.getParameterValues("select[]");
        if(selects==null) response.sendRedirect("/404.html");
        ArrayList<Integer> ids = new ArrayList<>();
        for(String s:selects){
            Integer id = Integer.parseInt(s);
            ids.add(id);
        }
        Student user = (Student)request.getSession().getAttribute("user");
        if(user==null) response.sendRedirect("/student/login");
        Integer result = selectionService.insert(ids, user.getId());
        if(result>0) user.setSelected(1);
        Boolean res = studentService.update(user);
        if(res) {
            request.getSession().setAttribute("user",user);
        }
        else response.sendRedirect("/404.html");
    }
    private void getRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/studentregister.jsp").forward(request,response);
    }
}
