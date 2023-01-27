package com.cos.reflect.filter;

import com.cos.reflect.annotation.RequestMapping;
import com.cos.reflect.controller.UserController;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Dispatcher implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String endPoint = request.getRequestURI().replaceAll(request.getContextPath(), "");

        UserController userController = new UserController();
        Method[] methods = userController.getClass().getDeclaredMethods();
        for (Method method : methods) {
            RequestMapping annotation = method.getDeclaredAnnotation(RequestMapping.class);
            if(annotation.value().equals(endPoint)){
                try{
                    String path = (String) method.invoke(userController);
                    RequestDispatcher dis = request.getRequestDispatcher(path);
                    dis.forward(request, response);
                } catch (Exception e){
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
