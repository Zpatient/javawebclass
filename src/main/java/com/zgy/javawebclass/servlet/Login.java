package com.zgy.javawebclass.servlet; /**
 * @author zgy
 * @create 2022-11-04 11:39
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = "";
        String password = "";
        Cookie[] cookies = request.getCookies();
        if(cookies!=null)
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("name"))
                    name = cookie.getValue();
                else if(cookie.getName().equals("password"))
                    password = cookie.getValue();
            }
        String html ="<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>登录页</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div id = \"main\">\n" +
                "    <div id=\"login_box\">\n" +
                "        <h2>LOGIN</h2>\n" +
                "        <form action=\"/Login\" method=\"post\" name=\"loginform\">\n" +
                "            <div id=\"input_box\">\n" +
                "                <input type=\"text\" name = \"name\" placeholder=\"请输入name\" value = \""+name+"\">\n" +
                "            </div>\n" +
                "            <div class=\"input_box\">\n" +
                "                <input type=\"password\" name = \"password\" placeholder=\"请输入密码\" value = \""+password+"\">\n" +
                "            </div>\n" +
                "            <span><input type=\"radio\" name = \"check\" value=\"checkd\" />记住密码</span>\n" +
                "            <input type=\"submit\" id = \"submit\" value=\"登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录\"/><br>\n" +
                "        </form>\n" +
                "    </div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";
        response.setHeader("content-type","text/html;charset=utf-8");
        response.getWriter().print(html);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String check = request.getParameter("check");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        if(check.equals("checkd")){
            Cookie username = new Cookie("name",name);
            Cookie userpassword = new Cookie("password",password);
            response.addCookie(username);
            response.addCookie(userpassword);
        }
        response.setHeader("content-type","text/html;charset=utf-8");
        response.getWriter().print("登录成功");
    }
}
