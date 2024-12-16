package br.com.bitnary.bitstream.infrastructure.server;

import br.com.bitnary.bitstream.application.auth.handlers.AuthenticationFailHandle;
import br.com.bitnary.bitstream.application.core.handlers.ConstraintViolationHandler;
import br.com.bitnary.bitstream.application.core.handlers.RuntimeExceptionHandle;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.validation.ValidationFeature;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyAPI extends ResourceConfig {
    public JerseyAPI() {
        packages("br.com.bitnary.bitstream.presentation.controller", "br.com.bitnary.bitstream.presentation.response");

//        register(TokenAuthenticationFilter.class); // Enable jwt authentication
        register(ValidationFeature.class); // Enable validation

        // Exceptions handlers
        register(RuntimeExceptionHandle.class);
        register(ConstraintViolationHandler.class);
        register(AuthenticationFailHandle.class);
    }
}
