package com.cos.reflect.filter;

import javax.servlet.*;
import java.io.IOException;

public class Dispatcher implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Dispatcher Enter!");
    }
}
