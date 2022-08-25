package com.polariodvoid.one_eighty;

import com.polariodvoid.one_eighty.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import com.polariodvoid.one_eighty.Service.UserService;
@Component
public class ControllerHelper {
    @Autowired
    private UserService userService;

    public User getAuthenticatedUser(HttpServletRequest request) {
        // String email = request.getQueryString(); //email
        String email = "abcd@gmail.com";
        return userService.findByEmail(email);
    }
}
