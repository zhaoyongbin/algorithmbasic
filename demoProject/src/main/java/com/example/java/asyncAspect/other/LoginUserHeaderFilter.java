package com.example.java.asyncAspect.other;
import com.example.java.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;


public class LoginUserHeaderFilter implements Filter  { //implements Filter
    private Logger logger= LoggerFactory.getLogger(LoginUserHeaderFilter.class);
    public static  ThreadLocal<User> threadLocal=new ThreadLocal<User>(){
        protected User initiaValue(){
            return null;
        }
    };

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//todo： jar包中没有实现
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //todo： 此方法重新实现 打成jar包

    }

    @Override
    public void destroy() {
//todo： jar包中没有实现
    }

//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//    }
}
