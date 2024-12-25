package br.com.bitnary.bitstream.application.user.usecases;

import br.com.bitnary.bitstream.application.user.exceptions.UserAlreadyExistsException;
import br.com.bitnary.bitstream.domain.user.User;
import br.com.bitnary.bitstream.domain.user.UserRepository;
import br.com.bitnary.bitstream.domain.userProfile.UserProfile;
import br.com.bitnary.bitstream.domain.userProfile.UserProfileRepository;
import br.com.bitnary.bitstream.shared.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CreateUserUseCase {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public User execute(User user) {
        Optional<User> checkUserExists = userRepository.findByEmail(user.getEmail());

        if (checkUserExists.isPresent()) {
            throw new UserAlreadyExistsException();
        }

        UserProfile userProfile = UserProfile.builder()
                .name(StringUtils.capitalizeFirst(user.getName().split(" ")[0]))
                .user(user)
                .build();

        user.setActive(true);
        user.setNewPassword(user.getPassword());

        User userCreated = userRepository.save(user);

        userProfile = userProfileRepository.save(userCreated.getId(), userProfile);
        userCreated.addProfile(userProfile);

        return userCreated;
    }
}
