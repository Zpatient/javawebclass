package com.zgy.javawebclass.servlet; /**
 * @author zgy
 * @create 2022-12-09 13:14
 */

import com.zgy.javawebclass.bean.Customer;
import com.zgy.javawebclass.dao.CustomerDao;
import com.zgy.javawebclass.service.CustomerService;
import com.zgy.javawebclass.service.impl.CustomerServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerServlet", value = "/a")
public class CustomerServlet extends HttpServlet {
    CustomerService customerService = new CustomerServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customers = customerService.getList();
//        for(Customer customer:customers){
//            System.out.println(customer);
//        }
        request.setAttribute("customers",customers);
        request.getRequestDispatcher("customer.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchId1 = request.getParameter("searchId");
        String deleteId1 = request.getParameter("deleteId");
        String id1 = request.getParameter("id");
        String age1 = request.getParameter("age");
        String name = request.getParameter("name");
        String  phoneNumber = request.getParameter("phoneNumber");
        if(searchId1 != null&&!searchId1.equals("") ){
            Integer searchId = Integer.parseInt(searchId1);
            Customer customer = customerService.search(searchId);
            System.out.println(customer);
            request.setAttribute("customer",customer);
        }
        if(deleteId1 != null&&!deleteId1.equals("")){
            System.out.println(deleteId1);
            Integer deleteId = Integer.parseInt(deleteId1);
            customerService.delete(deleteId);
        }
        if(id1!=null&&!id1.equals("")&&name!=null&&age1!=null&&!age1.equals("")&&phoneNumber!=null){
            Integer id = Integer.parseInt(id1);
            Integer  age = Integer.parseInt(age1);
            customerService.add(id,name,age,phoneNumber);
        }
        List<Customer> customers = customerService.getList();
        request.setAttribute("customers",customers);
        request.getRequestDispatcher("customer.jsp").forward(request,response);
    }
}
