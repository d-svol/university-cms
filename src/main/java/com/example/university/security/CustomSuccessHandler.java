package com.example.university.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String username = authentication.getName();
        String role = (authentication.getAuthorities().iterator().next().getAuthority());

        switch (role) {
            case "ADMIN" -> response.sendRedirect("/adminscab");
            case "STUDENT" -> response.sendRedirect("/studentscab");
            case "TEACHER" -> response.sendRedirect("/professorscab");
            case "STUFF" -> response.sendRedirect("/stuffscab");
        }

        String successMessage = String.format("You have successfully logged in as %s", username);
        request.getSession().setAttribute("successMessage", successMessage);
    }
}
