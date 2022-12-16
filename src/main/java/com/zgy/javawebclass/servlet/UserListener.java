package com.zgy.javawebclass.servlet; /**
 * @author zgy
 * @create 2022-12-16 11:58
 */

import com.zgy.javawebclass.bean.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

@WebListener
public class UserListener implements HttpSessionListener{

    public UserListener() {
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        ServletContext servletContext = session.getServletContext();
        User.UserBaseInfo currentUser = (User.UserBaseInfo)session.getAttribute("currentUser");
        Map<String,User> userMap = (Map<String,User>)servletContext.getAttribute("userMap");
        if(userMap!=null){
            userMap.remove(currentUser.getName());
        }
    }
}
