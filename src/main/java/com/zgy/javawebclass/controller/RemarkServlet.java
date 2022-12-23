package com.zgy.javawebclass.controller; /**
 * @author zgy
 * @create 2022-12-21 1:10
 */

import com.zgy.javawebclass.bean.*;
import com.zgy.javawebclass.service.QuestionService;
import com.zgy.javawebclass.service.RemarkService;
import com.zgy.javawebclass.service.impl.QuestionServiceImpl;
import com.zgy.javawebclass.service.impl.RemarkServiceImpl;
import com.zgy.javawebclass.utils.ParamsToBean;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@WebServlet(name = "RemarkServlet", value = "/remark/*")
public class RemarkServlet extends HttpServlet {
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

    private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String content = request.getParameter("content");
        if(StringUtils.isAnyBlank(id,content)) response.sendRedirect("/404.html");
        Integer remarkId = Integer.parseInt(id);
        Remark remark = remarkService.getById(remarkId);
        if(remark==null) response.sendRedirect("/404.html");
        remark.setContent(content);
        remark.setIsread(0);
        Boolean result = remarkService.update(remark);
        if(!result) response.sendRedirect("/404.html");
    }

    private void doInsert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String[]> map = request.getParameterMap();
        Remark remark = ParamsToBean.transform(map, Remark.class);
        remark.setRemarktime(LocalDateTime.now());
        remark.setContent(remark.getContent().trim());
        remark.setIsread(0);
        Boolean result = remarkService.insert(remark);
        if(!result) response.sendRedirect("/404.html");
    }

    private void doGetDetail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String type =(String)request.getSession().getAttribute("type");
        if(type==null) response.sendRedirect("/404.html");
        String id = request.getParameter("id");
        if(StringUtils.isAnyBlank(id)||id==null) response.sendRedirect("/404.html");
        if(type.equals("student")){
            Student user = (Student)request.getSession().getAttribute("user");
            Integer userId = user.getId();
            Ask ask = questionService.getById(Integer.parseInt(id));
            remarkService.updateRemarkIsRead(Integer.parseInt(id),userId);
            List<Message> messages = remarkService.getMessagesByQuestionId(Integer.parseInt(id));
            request.setAttribute("ask",ask);
            for(Message message:messages){
                if(message.getOwnerid().equals(userId)){
                    message.setShow(true);
                }else{
                    message.setShow(false);
                }
            }
            request.setAttribute("messages",messages);
            if(messages.size()>0) request.setAttribute("notNull", true);
            else request.setAttribute("notNull", false);
        }
        else{
            Teacher user = (Teacher)request.getSession().getAttribute("user");
            Integer userId = user.getId();
            Ask ask = questionService.getById(Integer.parseInt(id));
            remarkService.updateRemarkIsRead(Integer.parseInt(id),userId);
            List<Message> messages = remarkService.getMessagesByQuestionId(Integer.parseInt(id));
            request.setAttribute("ask",ask);
            for(Message message:messages){
                if(message.getOwnerid().equals(userId)){
                    message.setShow(true);
                }else{
                    message.setShow(false);
                }
            }
            request.setAttribute("messages",messages);
            if(messages.size()>0) request.setAttribute("notNull", true);
            else request.setAttribute("notNull", false);
        }
        request.getRequestDispatcher("/question-detail.jsp").forward(request,response);
    }

    private void doRemove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        if(id==null) response.sendRedirect("/404.html");
        else{
            Boolean result = remarkService.remove(Integer.parseInt(id));
        }
    }
}
