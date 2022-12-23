package com.zgy.javawebclass.controller; /**
 * @author zgy
 * @create 2022-12-21 1:10
 */

import com.zgy.javawebclass.bean.Ask;
import com.zgy.javawebclass.bean.Message;
import com.zgy.javawebclass.bean.Question;
import com.zgy.javawebclass.bean.Remark;
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
    private void doUpdate(HttpServletRequest request, HttpServletResponse response) {

    }

    private void doInsert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String[]> map = request.getParameterMap();
        Remark remark = ParamsToBean.transform(map, Remark.class);
        remark.setRemarktime(LocalDateTime.now());
        remark.setContent(remark.getContent().trim());
        Boolean result = remarkService.insert(remark);
        if(!result) response.sendRedirect("/404.html");
    }

    private void doGetDetail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        if(StringUtils.isAnyBlank(id)||id==null) response.sendRedirect("/404.html");
        Ask ask = questionService.getById(Integer.parseInt(id));
        List<Message> messages = remarkService.getMessagesByQuestionId(Integer.parseInt(id));
        request.setAttribute("ask",ask);
        request.setAttribute("messages",messages);
        request.getRequestDispatcher("/student-question-detail.jsp").forward(request,response);
    }

    private void doRemove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        if(id==null) response.sendRedirect("/404.html");
        else{
            Boolean result = questionService.remove(Integer.parseInt(id));
        }
    }
}
