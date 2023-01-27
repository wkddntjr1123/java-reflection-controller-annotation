package com.cos.reflect.filter;

import com.cos.reflect.controller.UserController;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Dispatcher implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

//        System.out.println("context path : " + request.getContextPath());
//        System.out.println("uri path : " + request.getRequestURI());
//        System.out.println("full url : " + request.getRequestURL());

        String endPoint = request.getRequestURI().replaceAll(request.getContextPath(),"");
//        System.out.println(endPoint);

        UserController userController = new UserController();
        if(endPoint.equals("/join")){
            userController.join();
        } else if (endPoint.equals("/login")){
            userController.login();
        }
    }
}
