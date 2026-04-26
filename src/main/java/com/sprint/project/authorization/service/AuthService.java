package com.sprint.project.authorization.service;

import com.sprint.project.authorization.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JwtUtil jwtUtil;

    public String authenticate(String username, String password) {

        if(username.equals("Sureka") && password.equals("123"))
            return jwtUtil.generateToken(username);

        if(username.equals("Jai") && password.equals("123"))
            return jwtUtil.generateToken(username);

        if(username.equals("Iniya") && password.equals("123"))
            return jwtUtil.generateToken(username);

        if(username.equals("Ashmitha") && password.equals("123"))
            return jwtUtil.generateToken(username);

        if(username.equals("John") && password.equals("123"))
            return jwtUtil.generateToken(username);

        return null;
    }
}