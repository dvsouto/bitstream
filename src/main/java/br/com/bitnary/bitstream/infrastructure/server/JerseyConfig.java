package br.com.bitnary.bitstream.infrastructure.server;

import br.com.bitnary.bitstream.application.auth.handlers.AuthenticationFailHandle;
import br.com.bitnary.bitstream.application.core.handlers.ConstraintViolationHandler;
import br.com.bitnary.bitstream.application.core.handlers.ExceptionHandler;
import br.com.bitnary.bitstream.application.core.handlers.RuntimeExceptionHandler;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.validation.ValidationFeature;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("br.com.bitnary.bitstream.presentation.controller");

//        register(TokenAuthenticationFilter.class); // Enable jwt authentication
        register(ValidationFeature.class); // Enable validation

        // Exceptions handlers
        register(RuntimeExceptionHandler.class);
        register(ExceptionHandler.class);
        register(ConstraintViolationHandler.class);
        register(AuthenticationFailHandle.class);
    }

    @Bean
    public ServletRegistrationBean<ServletContainer> jerseyServlet() {
        ServletRegistrationBean<org.glassfish.jersey.servlet.ServletContainer> registrationBean = new ServletRegistrationBean<>(new org.glassfish.jersey.servlet.ServletContainer(), "/api/*");
        registrationBean.addInitParameter("jersey.config.server.provider.packages", "br.com.bitnary");

        return registrationBean;
    }
}
