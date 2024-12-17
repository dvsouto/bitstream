package br.com.bitnary.bitstream.application.auth.usecases;

import br.com.bitnary.bitstream.application.auth.dtos.AuthenticatedUserResponseDTO;
import br.com.bitnary.bitstream.application.auth.exceptions.AuthenticationFailException;
import br.com.bitnary.bitstream.domain.auth.BearerToken;
import br.com.bitnary.bitstream.domain.user.User;
import br.com.bitnary.bitstream.infrastructure.repositories.implementations.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticateUserUseCase {
    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticatedUser execute(String email, String password) throws AuthenticationFailException {
        Authentication authenticated = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        User user = (User) authenticated.getPrincipal();
        BearerToken token = new BearerToken(user);

        return new AuthenticatedUser(user, token);
    }

    public record AuthenticatedUser ( User user, BearerToken token ) {};
}
