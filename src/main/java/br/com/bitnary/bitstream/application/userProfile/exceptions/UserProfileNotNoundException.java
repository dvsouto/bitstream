package br.com.bitnary.bitstream.application.userProfile.exceptions;

import br.com.bitnary.bitstream.application.core.exceptions.ApiException;
import jakarta.ws.rs.core.Response;

public class UserProfileNotNoundException extends ApiException {
    protected Response.Status status = Response.Status.BAD_REQUEST;

    public UserProfileNotNoundException() {
        super("Profile not found");
    }
}
