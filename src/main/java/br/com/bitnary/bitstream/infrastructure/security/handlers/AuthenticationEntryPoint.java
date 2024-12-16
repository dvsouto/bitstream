package br.com.bitnary.bitstream.infrastructure.security.handlers;

import br.com.bitnary.bitstream.presentation.response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println("@e type" + authException.getClass());
        response.setStatus(401);
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(
                new ApiResponse()
                        .setMessage("Unauthorized1 - " + authException.getMessage())
                        .response()
                        .getEntity()
        ));

    }
}