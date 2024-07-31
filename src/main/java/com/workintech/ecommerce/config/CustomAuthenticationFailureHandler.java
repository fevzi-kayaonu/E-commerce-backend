package com.workintech.ecommerce.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {


    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        String jsonResponse = objectMapper.writeValueAsString(
                new ErrorResponse("Authentication failed", exception.getMessage())
        );

        response.getWriter().write(jsonResponse);
    }

    private static class ErrorResponse {
        private final String message;
        private final String error;

        public ErrorResponse(String message, String error) {
            this.message = message;
            this.error = error;
        }

        public String getMessage() {
            return message;
        }

        public String getError() {
            return error;
        }
    }
}
