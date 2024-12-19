package br.com.bitnary.bitstream.infraestructure.repository.implementations;

import br.com.bitnary.bitstream.application.core.mappers.UserMapper;
import br.com.bitnary.bitstream.application.core.mappers.UserProfileMapper;
import br.com.bitnary.bitstream.domain.user.User;
import br.com.bitnary.bitstream.domain.userProfile.UserProfile;
import br.com.bitnary.bitstream.infrastructure.entities.UserEntity;
import br.com.bitnary.bitstream.infrastructure.entities.UserProfileEntity;
import br.com.bitnary.bitstream.infrastructure.repositories.implementations.UserProfileRepositoryImpl;
import br.com.bitnary.bitstream.infrastructure.repositories.implementations.UserRepositoryImpl;
import br.com.bitnary.bitstream.infrastructure.repositories.jpa.UserProfileRepositoryJPA;
import br.com.bitnary.bitstream.infrastructure.repositories.jpa.UserRepositoryJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class UserProfileRepositoryImplIntegrationTest {
    @Autowired
    UserRepositoryJPA userRepositoryJPA;

    @Autowired
    UserProfileRepositoryJPA userPropfileRepositoryJPA;

    @Autowired
    UserProfileRepositoryImpl userProfileRepositoryImpl;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserProfileMapper userProfileMapper;

    String userId;
    UserProfile userProfile;
    UserProfileEntity userProfileEntity;

    @BeforeEach
    public void setUp() {
        userId = userRepositoryJPA.save(new UserEntity()).getId().toString();
        userProfile = UserProfile.builder()
                .user(User.builder().id(userId).build())
                .name("Super John Doe")
                .build();

        userProfileEntity = userProfileMapper.toEntity(userProfile);

        userPropfileRepositoryJPA.save(userProfileEntity);
    }

    @Test
    void testSaveUserProfileIntegration() {
        UserProfile savedUserProfile = userProfileRepositoryImpl.save(userId, userProfile);

        assertThat(savedUserProfile).isNotNull();
        assertThat(savedUserProfile.getName()).isEqualTo(userProfileEntity.getName());
    }

    @Test
    void testFindUserProfileByIdIntegration() {
        UserProfileEntity savedUserProfile = userPropfileRepositoryJPA.save(userProfileEntity);

        userPropfileRepositoryJPA.flush();

        UserProfile foundUserProfile = userProfileRepositoryImpl.findById(savedUserProfile.getId().toString()).orElse(null);

        assertThat(foundUserProfile).isNotNull();
        assertThat(foundUserProfile.getId()).isEqualTo(savedUserProfile.getId().toString());
    }

    @Test
    void testFindUserProfileByUserIdIntegration() {
        List<UserProfile> profiles = userProfileRepositoryImpl.findByUserId(userId);

        assertThat(profiles.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    void testFindAllUserProfilesIntegration() {
        List<UserProfile> profiles = userProfileRepositoryImpl.findAll();

        assertThat(profiles.size()).isGreaterThanOrEqualTo(1);
    }
}
