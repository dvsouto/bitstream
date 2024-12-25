package br.com.bitnary.bitstream.infraestructure.repository.implementations;

import br.com.bitnary.bitstream.application.core.mappers.UserProfileMapper;
import br.com.bitnary.bitstream.domain.user.User;
import br.com.bitnary.bitstream.domain.profile.UserProfile;
import br.com.bitnary.bitstream.infrastructure.entities.UserEntity;
import br.com.bitnary.bitstream.infrastructure.entities.UserProfileEntity;
import br.com.bitnary.bitstream.infrastructure.repositories.implementations.UserProfileRepositoryImpl;
import br.com.bitnary.bitstream.infrastructure.repositories.jpa.UserProfileRepositoryJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserProfileRepositoryImplTest {
    @Mock
    private UserProfileRepositoryJPA userProfileRepositoryJPA;

    @Mock
    private UserProfileMapper userProfileMapper;

    @InjectMocks
    private UserProfileRepositoryImpl userProfileRepository;

    private User user;
    private UserEntity userEntity;
    private UserProfile userProfile;
    private UserProfileEntity userProfileEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = User.builder()
                .build();

        userProfile = UserProfile.builder()
                .name("Super John Doe")
                .build();

        user.setId(UUID.randomUUID().toString());
        userProfile.setId(UUID.randomUUID().toString());

        userEntity = new UserEntity();
        userEntity.setId(UUID.fromString(user.getId()));

        userProfileEntity = new UserProfileEntity();
        userProfileEntity.setId(UUID.fromString(userProfile.getId()));
        userProfileEntity.setName(userProfile.getName());

        when(userProfileMapper.toDomain(any(UserProfileEntity.class))).thenReturn(userProfile);
        when(userProfileMapper.toEntity(any(UserProfile.class))).thenReturn(userProfileEntity);
    }

    @Test
    void testCreateProfile() {
        when(userProfileRepositoryJPA.save(any(UserProfileEntity.class))).thenReturn(userProfileEntity);

        UserProfile savedUser = userProfileRepository.save(user.getId(), userProfile);

        verify(userProfileRepositoryJPA).save(any(UserProfileEntity.class));

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getName()).isEqualTo(userProfile.getName());
    }

    @Test
    void testFindUserProfileById() {
        when(userProfileRepositoryJPA.findById(any(UUID.class))).thenReturn(Optional.of(userProfileEntity));

        UserProfile findUser = userProfileRepository.findById(user.getId()).orElse(null);

        verify(userProfileRepositoryJPA).findById(any(UUID.class));

        assertThat(findUser).isNotNull();
        assertThat(findUser.getName()).isEqualTo(userProfile.getName());
    }

    @Test
    void testFindUserProfileByUserId() {
        when(userProfileRepositoryJPA.findByUser(any(UserEntity.class))).thenReturn(List.of(userProfileEntity));

        List<UserProfile> findUser = userProfileRepository.findByUserId(user.getId());

        verify(userProfileRepositoryJPA).findByUser(any(UserEntity.class));

        assertThat(findUser).isNotNull();
        assertThat(findUser.size()).isGreaterThanOrEqualTo(1);
    }
}
