package br.com.bitnary.bitstream.application.user.usecases;

import br.com.bitnary.bitstream.application.auth.dtos.AuthenticatedUserResponseDTO;
import br.com.bitnary.bitstream.application.auth.exceptions.AuthenticationFailException;
import br.com.bitnary.bitstream.application.user.dtos.CreateUserRequestDTO;
import br.com.bitnary.bitstream.application.user.exceptions.UserAlreadyExistsException;
import br.com.bitnary.bitstream.domain.auth.BearerToken;
import br.com.bitnary.bitstream.domain.user.User;
import br.com.bitnary.bitstream.infrastructure.repositories.implementations.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateUserUseCase {
    @Autowired
    private UserRepositoryImpl userRepository;

    public User execute(User user) {
        Optional<User> checkUserExists = userRepository.findByEmail(user.getEmail());

        if (checkUserExists.isPresent()) {
            throw new UserAlreadyExistsException();
        }

        user.setActive(true);
        user.setNewPassword(user.getPassword());

        return userRepository.save(user);
    }
}
