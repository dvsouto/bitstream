package br.com.bitnary.bitstream.application.auth.handlers;

import br.com.bitnary.bitstream.application.auth.exceptions.AuthenticationFailException;
import br.com.bitnary.bitstream.presentation.response.ApiResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class AuthenticationFailHandle implements ExceptionMapper<AuthenticationFailException> {

    @Override
    public Response toResponse(AuthenticationFailException exception) {
        return new ApiResponse()
                .fail(Response.Status.UNAUTHORIZED)
                .setMessage("E-mail or password incorrect")
                .response();
    }
}
