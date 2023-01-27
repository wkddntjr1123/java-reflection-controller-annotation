package com.cos.reflect.filter;

import com.cos.reflect.controller.UserController;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Dispatcher implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String endPoint = request.getRequestURI().replaceAll(request.getContextPath(), "");

        UserController userController = new UserController();
        Method[] methods = userController.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (endPoint.equals("/" + method.getName())) {
                try {
                    method.invoke(userController);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
