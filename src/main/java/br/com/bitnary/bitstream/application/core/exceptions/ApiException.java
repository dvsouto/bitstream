package br.com.bitnary.bitstream.application.core.exceptions;

import jakarta.ws.rs.core.Response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiException extends RuntimeException {
    protected Response.Status status;

    public ApiException() {
        super();

        this.status = Response.Status.BAD_REQUEST;
    }

    public ApiException(String msg) {
        super(msg);

        this.status = Response.Status.BAD_REQUEST;
    }

    public ApiException(String msg, Response.Status status) {
        super(msg);

        this.status = status;
    }
}
