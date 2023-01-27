package com.cos.reflect.filter;

import com.cos.reflect.annotation.RequestMapping;
import com.cos.reflect.controller.UserController;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Enumeration;

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
            if (annotation.value().equals(endPoint)) {
                try {
                    Parameter[] params = method.getParameters();
                    String path = null;
                    if (params.length != 0) {
                        Object dtoInstance = params[0].getType().getDeclaredConstructor().newInstance();
                        Enumeration<String> keys = request.getParameterNames();
                        setData(dtoInstance, request);
                        path = (String) method.invoke(userController, dtoInstance);
                    } else {
                        path = (String) method.invoke(userController);
                    }
                    RequestDispatcher dis = request.getRequestDispatcher(path);
                    dis.forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    private <T> void setData(T instance, HttpServletRequest request) {
        Enumeration<String> keys = request.getParameterNames();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            String methodKey = keyToMethodKey(key);

            Method[] methods = instance.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals(methodKey)) {
                    try {
                        method.invoke(instance, request.getParameter(key));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }

    private String keyToMethodKey(String key) {
        return "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
    }
}
