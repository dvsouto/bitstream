package br.com.bitnary.bitstream.infrastructure.security.handlers;

import br.com.bitnary.bitstream.presentation.response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();

        response.setStatus(401);
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(
                new ApiResponse()
                        .setMessage("Access Denied")
                        .response()
                        .getEntity()
        ));
    }
}