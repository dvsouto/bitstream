package br.com.bitnary.bitstream.application.user.exceptions;

import br.com.bitnary.bitstream.application.core.exceptions.ApiException;
import jakarta.ws.rs.core.Response;

public class UserAlreadyExistsException extends ApiException {
    protected Response.Status status = Response.Status.BAD_REQUEST;

    public UserAlreadyExistsException() {
        super("User already exists");
    }
}
