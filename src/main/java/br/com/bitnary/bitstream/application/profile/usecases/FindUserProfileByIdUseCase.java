package br.com.bitnary.bitstream.application.profile.usecases;

import br.com.bitnary.bitstream.application.auth.usecases.GetAuthenticatedUserUseCase;
import br.com.bitnary.bitstream.application.profile.exceptions.UserProfileNotNoundException;
import br.com.bitnary.bitstream.domain.user.User;
import br.com.bitnary.bitstream.domain.profile.UserProfile;
import br.com.bitnary.bitstream.domain.profile.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FindUserProfileByIdUseCase {
    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    GetAuthenticatedUserUseCase getAuthenticatedUserUseCase;

    public UserProfile execute(String userProfileId) {
        UserProfile userProfile = userProfileRepository.findById(userProfileId).orElseThrow(UserProfileNotNoundException::new);
        User user = getAuthenticatedUserUseCase.execute().orElseThrow();

        if (! user.getId().equals(userProfile.getUser().getId())) {
            throw new UserProfileNotNoundException();
        }

        userProfile.setUser(user);

        return userProfile;
    }
}
