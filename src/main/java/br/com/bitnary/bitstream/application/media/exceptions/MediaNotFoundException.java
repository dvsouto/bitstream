package br.com.bitnary.bitstream.application.media.exceptions;

import jakarta.ws.rs.core.Response;

public class MediaNotFoundException extends Exception {
    protected Response.Status status = Response.Status.BAD_REQUEST;

    public MediaNotFoundException() {
        super("Media not found");
    }
}
