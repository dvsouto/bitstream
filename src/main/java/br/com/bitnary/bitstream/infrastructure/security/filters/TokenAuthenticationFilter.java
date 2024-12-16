package br.com.bitnary.bitstream.infrastructure.security.filters;

import br.com.bitnary.bitstream.infrastructure.security.exceptions.InvalidTokenException;
import br.com.bitnary.bitstream.infrastructure.server.SecurityConfig;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final String SECRET_KEY = "secrectkey";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean authorized = false;
        String token = this.getToken(request);

        if (shouldSkipAuthentication(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (token != null) {
            try {
                if (token.equals("teste")) {
                    SecurityContextHolder.getContext().setAuthentication(
                            new UsernamePasswordAuthenticationToken(
                                    "davi.souto@gmail.com",
                                    null,
                                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                            )
                    );
                } else {
                    throw new InvalidTokenException();
                }
            } catch (Exception e) {
                throw new InvalidTokenException();
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean shouldSkipAuthentication(HttpServletRequest request) {
        String uri = request.getRequestURI();
        AntPathMatcher antPathMatcher = new AntPathMatcher();

        for (String pattern : SecurityConfig.AUTH_NOT_REQUIRED) {
            if (antPathMatcher.match(pattern, uri)) {
                return true;
            }
        }

        return false;
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);

            return token;
        }

        return null;
    }
}
