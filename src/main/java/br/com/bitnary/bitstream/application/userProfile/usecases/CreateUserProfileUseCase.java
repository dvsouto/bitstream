package br.com.bitnary.bitstream.application.userProfile.usecases;

import br.com.bitnary.bitstream.application.auth.usecases.GetAuthenticatedUserUseCase;
import br.com.bitnary.bitstream.application.userProfile.dtos.CreateUserProfileRequestDTO;
import br.com.bitnary.bitstream.domain.user.User;
import br.com.bitnary.bitstream.domain.userProfile.UserProfile;
import br.com.bitnary.bitstream.domain.userProfile.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateUserProfileUseCase {
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private GetAuthenticatedUserUseCase getAuthenticatedUserUseCase;

    public UserProfile execute(UserProfile userProfile) {
        User user = getAuthenticatedUserUseCase.execute().orElseThrow();
        userProfile.validate();
        UserProfile userProfileSaved = this.userProfileRepository.save(user.getId(), userProfile);

        return userProfileSaved;
    }
}
