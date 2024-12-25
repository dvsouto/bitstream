package br.com.bitnary.bitstream.application.auth.usecases;

import br.com.bitnary.bitstream.application.auth.exceptions.AuthenticationFailException;
import br.com.bitnary.bitstream.domain.auth.BearerToken;
import br.com.bitnary.bitstream.domain.user.User;
import br.com.bitnary.bitstream.domain.user.UserRepository;
import br.com.bitnary.bitstream.domain.profile.UserProfile;
import br.com.bitnary.bitstream.domain.profile.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthenticateUserUseCase {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticatedUser execute(String email, String password) throws AuthenticationFailException {
        Authentication authenticated = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        User user = (User) authenticated.getPrincipal();
        BearerToken token = new BearerToken(user);
        List<UserProfile> profiles = this.userProfileRepository.findByUserId(user.getId());

        user.setProfiles(profiles);

        return new AuthenticatedUser(user, token);
    }

    public record AuthenticatedUser ( User user, BearerToken token ) {};
}
