package br.com.bitnary.bitstream.application.core.handlers;

import br.com.bitnary.bitstream.presentation.response.ApiResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class RuntimeExceptionHandle implements ExceptionMapper<RuntimeException> {

    private static final Logger LOGGER = Logger.getLogger(RuntimeExceptionHandle.class.getName());

    @Override
    public Response toResponse(RuntimeException exception) {
        LOGGER.log(Level.SEVERE, "Exception caught: ", exception);

        String message = exception.getMessage().split("\n")[0];

        return new ApiResponse()
                .fail(Response.Status.INTERNAL_SERVER_ERROR)
                .setMessage(message)
                .response();
    }
}
