package com.zgy.javawebclass.controller; /**
 * @author zgy
 * @create 2022-12-21 1:10
 */

import com.zgy.javawebclass.bean.Ask;
import com.zgy.javawebclass.bean.Course;
import com.zgy.javawebclass.bean.Message;
import com.zgy.javawebclass.bean.Question;
import com.zgy.javawebclass.service.QuestionService;
import com.zgy.javawebclass.service.RemarkService;
import com.zgy.javawebclass.service.impl.QuestionServiceImpl;
import com.zgy.javawebclass.service.impl.RemarkServiceImpl;
import com.zgy.javawebclass.utils.ParamsToBean;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@WebServlet(name = "QuestionServlet", value = "/question/*")
public class QuestionServlet extends HttpServlet {
    QuestionService questionService = new QuestionServiceImpl();
    RemarkService remarkService = new RemarkServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String target = requestURI.substring(requestURI.lastIndexOf("/")+1);
        switch (target){
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
    private void doUpdate(HttpServletRequest request, HttpServletResponse response) {

    }

    private void doInsert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String[]> map = request.getParameterMap();
        Question question = ParamsToBean.transform(map, Question.class);
        question.setAsktime(LocalDateTime.now());
        Boolean result = questionService.insert(question);
        if(!result) response.sendRedirect("/404.html");
    }

    private void doGetDetail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        if(StringUtils.isAnyBlank(id)||id==null) response.sendRedirect("/404.html");
        Ask ask = questionService.getById(Integer.parseInt(id));
        List<Message> messages = remarkService.getMessagesByQuestionId(Integer.parseInt(id));
        request.setAttribute("ask",ask);
        request.setAttribute("messages",messages);
        request.getRequestDispatcher("/question-detail.jsp").forward(request,response);
    }

    private void doRemove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        if(id==null) response.sendRedirect("/404.html");
        else{
            Boolean result = questionService.remove(Integer.parseInt(id));
        }
    }
}
