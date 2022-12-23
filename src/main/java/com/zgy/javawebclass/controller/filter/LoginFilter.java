package com.zgy.javawebclass.controller.filter;
/**
 * @author zgy
 * @create 2022-12-24 3:51
 */

import org.apache.commons.collections.list.SynchronizedList;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "LoginFilter",urlPatterns = "/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        List<String> urls = new ArrayList<>();
        urls.add("/student/login");
        urls.add("/admin/login");
        urls.add("/teacher/login");
        HttpServletRequest httpServletRequest= (HttpServletRequest)request;
        HttpServletResponse httpServletResponse= (HttpServletResponse)response;
        String requestURI = httpServletRequest.getRequestURI();
        if(requestURI.equals(urls.get(0))||requestURI.equals(urls.get(1))||requestURI.equals(urls.get(2))){
            chain.doFilter(request, response);
        }
        else{
            Object user = httpServletRequest.getSession().getAttribute("user");
            if(user==null) httpServletResponse.sendRedirect("/student/login");
            else chain.doFilter(request, response);
        }
    }
}
