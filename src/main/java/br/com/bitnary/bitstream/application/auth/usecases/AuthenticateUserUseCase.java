package br.com.bitnary.bitstream.application.auth.usecases;

import br.com.bitnary.bitstream.application.auth.dtos.AuthenticatedUserResponseDTO;
import br.com.bitnary.bitstream.application.auth.exceptions.AuthenticationFailException;
import br.com.bitnary.bitstream.domain.auth.BearerToken;
import br.com.bitnary.bitstream.domain.user.User;

public class AuthenticateUserUseCase {
    public AuthenticatedUser execute(String email, String password) throws AuthenticationFailException {
        if (email.equalsIgnoreCase("davi.souto@gmail.com") && password.equals("123456")) {
            BearerToken token = new BearerToken();
            User user = new User("davi.souto@gmail.com", "", "Davi Souto", 28, true);

            return new AuthenticatedUser(user, token);
        }

        throw new AuthenticationFailException();
    }

    public record AuthenticatedUser ( User user, BearerToken token ) {};
}
