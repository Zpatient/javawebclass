package com.zgy.javawebclass.controller.listener; /**
 * @author zgy
 * @create 2022-12-24 4:15
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.time.LocalDateTime;

@WebListener
public class LogoutListener implements HttpSessionListener{

    public LogoutListener() {
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        System.out.println("存在用户正在登录");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        Object user = session.getAttribute("user");
        System.out.println(user +"退出登录");
    }
}
