package br.com.bitnary.bitstream.application.user.exceptions;

import jakarta.ws.rs.core.Response;

public class UserNotFoundException extends Exception {
    protected Response.Status status = Response.Status.BAD_REQUEST;

    public UserNotFoundException() {
        super();
    }
}
