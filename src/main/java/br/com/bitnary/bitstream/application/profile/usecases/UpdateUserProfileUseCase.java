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
public class UpdateUserProfileUseCase {
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private GetAuthenticatedUserUseCase getAuthenticatedUserUseCase;

    public UserProfile execute(String userProfileId, UserProfile userProfile) {
        User user = getAuthenticatedUserUseCase.execute().orElseThrow();

        if (! user.getId().equals(userProfile.getUser().getId())) {
            throw new UserProfileNotNoundException();
        }

        userProfile.validate();

        return this.userProfileRepository.save(user.getId(), userProfile);
    }
}
