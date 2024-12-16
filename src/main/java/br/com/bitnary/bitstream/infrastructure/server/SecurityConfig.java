package br.com.bitnary.bitstream.infrastructure.server;

import br.com.bitnary.bitstream.infrastructure.security.filters.TokenAuthenticationFilter;
import br.com.bitnary.bitstream.infrastructure.security.handlers.AccessDeniedHandler;
import br.com.bitnary.bitstream.infrastructure.security.handlers.AuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    public static final String [] AUTH_NOT_REQUIRED = {
            "/api/auth/login",
            "/api",
            "/api/public/**"
    };

    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;

    public SecurityConfig(AuthenticationEntryPoint authenticationEntryPoint, AccessDeniedHandler accessDeniedHandler) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(AUTH_NOT_REQUIRED).permitAll()
                        .anyRequest().authenticated()
                )
//                .exceptionHandling(exception -> exception
//                        .authenticationEntryPoint(authenticationEntryPoint) // 401 handler
//                        .accessDeniedHandler(accessDeniedHandler)            // 403 handler
//                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .oauth2ResourceServer(spec -> spec.jwt(Customizer.withDefaults()))
                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//
//        return new CorsFilter(source);
//    }
}
