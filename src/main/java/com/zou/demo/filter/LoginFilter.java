package com.zou.demo.filter;


/*import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "loginFilter",
        urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "loginUI", value = "/admin/adminlogin"),
                @WebInitParam(name = "loginProcess", value = "/login"),
                @WebInitParam(name = "encoding", value = "utf-8")
        })

public class LoginFilter implements Filter {
    private FilterConfig config;

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        // 获取配置参数
        String loginUI = config.getInitParameter("loginUI");
        String loginProcess = config.getInitParameter("loginProcess");
        String encoding = config.getInitParameter("encoding");


        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // 设置请求的字符集（post请求方式有效）
        request.setCharacterEncoding(encoding);

        // 不带http://域名:端口的地址
        String uri = request.getRequestURI();
        if (uri.contains(loginUI) || uri.contains(loginProcess)) {

            HttpSession session = request.getSession();
            // 请求的登录，放行
            chain.doFilter(request, response);
        } else {
            if (request.getSession().getAttribute("username") == null) {
                // 重定向到登录页面
                response.sendRedirect(request.getContextPath() + loginUI);
            } else {
                // 已经登录，放行
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
        this.config = null;
    }
}*/

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@ConditionalOnProperty(prefix = "login.filter", name = "enabled", havingValue = "true")
@Configuration
@WebFilter("/login")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (HttpMethod.POST.matches(request.getMethod())) {
            Optional.ofNullable(request.getParameter("name"))
                    .filter(userName -> Objects.equals(userName, "admin"))
                    .ifPresent(userName -> {
                        throw new RuntimeException("禁止使用超级管理员账户登录系统！");
                    });
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
