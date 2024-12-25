package br.com.bitnary.bitstream.application.userProfile.usecases;

import br.com.bitnary.bitstream.application.auth.usecases.GetAuthenticatedUserUseCase;
import br.com.bitnary.bitstream.application.userProfile.exceptions.UserProfileNotNoundException;
import br.com.bitnary.bitstream.domain.user.User;
import br.com.bitnary.bitstream.domain.userProfile.UserProfile;
import br.com.bitnary.bitstream.domain.userProfile.UserProfileRepository;
import br.com.bitnary.bitstream.shared.StringUtils;
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
