package br.com.bitnary.bitstream.application.auth.services;

import br.com.bitnary.bitstream.domain.user.User;
import br.com.bitnary.bitstream.domain.user.UserRepository;
import br.com.bitnary.bitstream.domain.profile.UserProfile;
import br.com.bitnary.bitstream.domain.profile.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthenticationService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Not found"));
    }

    public User findUserByEmail(String email) {
        User user = this.userRepository.findByEmail(email).orElseThrow();
        List<UserProfile> profiles = this.userProfileRepository.findByUserId(user.getId());

        user.setProfiles(profiles);

        return user;
    }
}
