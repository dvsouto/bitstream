package br.com.bitnary.bitstream.application.core.handlers;

import br.com.bitnary.bitstream.application.core.exceptions.ApiException;
import br.com.bitnary.bitstream.presentation.response.ApiResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class ApiExceptionHandler implements ExceptionMapper<ApiException> {

    private static final Logger LOGGER = Logger.getLogger(ApiExceptionHandler.class.getName());

    @Override
    public Response toResponse(ApiException exception) {
        LOGGER.log(Level.SEVERE, "API Exception caught: ", exception);

        String message = exception.getMessage().split("\n")[0];

        return new ApiResponse()
                .fail(exception.getStatus())
                .setMessage(message)
                .response();
    }
}
