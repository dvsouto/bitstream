package br.com.bitnary.bitstream.infrastructure.security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidTokenException extends AuthenticationException {

    public InvalidTokenException() {
        super("Invalid or expired token");
    }


}