package com.sprint.project.authorization.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;


public class JwtFilter implements Filter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
     // ✅ Allow preflight requests
        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            res.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
            res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            res.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
            res.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        String path = req.getRequestURI();

     // ✅ Allow login API without token
     if (path.contains("/api/auth/login")) {
         chain.doFilter(request, response);
         return;
     }

        String authHeader = req.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            res.sendError(401, "Unauthorized");
            return;
        }

        try {
            String token = authHeader.substring(7);
            String username = jwtUtil.extractUsername(token);

            if (username == null) {
                res.sendError(401, "Invalid Token");
                return;
            }

        } catch (Exception e) {
            res.sendError(401, "Invalid Token");
            return;
        }

        chain.doFilter(request, response);
    }
}