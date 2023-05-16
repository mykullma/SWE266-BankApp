package com.swe266group12.bankapp.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        if (!req.getRequestURI().matches("/|/login|/register|/h2-console/.*") && req.getSession().getAttribute("user") == null) {
            System.out.println("Blocked at " + req.getRequestURI());
            ((HttpServletResponse) servletResponse).sendRedirect("/");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
