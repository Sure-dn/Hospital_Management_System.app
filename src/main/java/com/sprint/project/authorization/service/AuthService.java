package com.sprint.project.authorization.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public String authenticate(String username, String password) {

        // match with your frontend names
        if(username.equals("Sureka") && password.equals("123")) return "sureka";
        if(username.equals("Jai") && password.equals("123")) return "jai";
        if(username.equals("Iniya") && password.equals("123")) return "iniya";
        if(username.equals("Ashmitha") && password.equals("123")) return "ashmitha";
        if(username.equals("John") && password.equals("123")) return "john";

        return null;
    }
}