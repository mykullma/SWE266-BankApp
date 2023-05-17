package com.swe266group12.bankapp.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;

@Component
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        if (req.getRequestURI().matches("/home|/profile/.*") && req.getSession().getAttribute("user") == null) {
            System.out.println("Blocked " + URI.create(req.getRequestURI()).getPath());

            ((HttpServletResponse) servletResponse).sendRedirect("/");
            return;
        }

        System.out.println("Visited " + URI.create(req.getRequestURI()).getPath());
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
