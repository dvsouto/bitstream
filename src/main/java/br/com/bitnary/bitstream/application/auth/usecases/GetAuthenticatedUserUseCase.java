package br.com.bitnary.bitstream.application.auth.usecases;

import br.com.bitnary.bitstream.domain.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetAuthenticatedUserUseCase {
    public Optional<User> execute() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();

            return Optional.ofNullable(user);
        }
        return Optional.empty();
    }
}
