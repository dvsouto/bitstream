package br.com.bitnary.bitstream.infraestructure.repository.implementations;

import br.com.bitnary.bitstream.application.core.mappers.UserMapper;
import br.com.bitnary.bitstream.domain.user.User;
import br.com.bitnary.bitstream.infrastructure.entities.UserEntity;
import br.com.bitnary.bitstream.infrastructure.repositories.implementations.UserRepositoryImpl;
import br.com.bitnary.bitstream.infrastructure.repositories.jpa.UserRepositoryJPA;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
//import org.mapstruct.factory.Mappers;
//import org.mockito.Spy;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRepositoryImplTest {
    @Mock
    private UserRepositoryJPA userRepositoryJPA;

    @Mock
    private UserMapper userMapper;

    // Use spy only in integration
    // @Spy
    // private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @InjectMocks
    private UserRepositoryImpl userRepositoryImpl;

    private User user;
    private UserEntity userEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = User.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .password("password")
                .build();

        user.setId(UUID.randomUUID().toString());

        userEntity = new UserEntity();
        userEntity.setId(UUID.fromString(user.getId()));
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());

        when(userMapper.toDomain(any(UserEntity.class))).thenReturn(user);
        when(userMapper.toEntity(any(User.class))).thenReturn(userEntity);
    }

    @Test
    void testSaveUser() {
        when(userRepositoryJPA.save(any(UserEntity.class))).thenReturn(userEntity);

        User savedUser = userRepositoryImpl.save(user);

        verify(userRepositoryJPA).save(any(UserEntity.class));

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getName()).isEqualTo("John Doe");
        assertThat(savedUser.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(savedUser.getPassword()).isEqualTo("password");
    }

    @Test
    void testFindUserById() {
        when(userRepositoryJPA.findById(any(UUID.class))).thenReturn(Optional.of(userEntity));

        User findUser = userRepositoryImpl.findById(user.getId()).orElse(null);

        verify(userRepositoryJPA).findById(any(UUID.class));

        assertThat(findUser).isNotNull();
        assertThat(findUser.getName()).isEqualTo("John Doe");
        assertThat(findUser.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(findUser.getPassword()).isEqualTo("password");
    }

    @Test
    void testFindUserByEmail() {
        when(userRepositoryJPA.findByEmail(any(String.class))).thenReturn(Optional.of(userEntity));

        User findUser = userRepositoryImpl.findByEmail(user.getEmail()).orElse(null);

        verify(userRepositoryJPA).findByEmail(any(String.class));

        assertThat(findUser).isNotNull();
        assertThat(findUser.getName()).isEqualTo("John Doe");
        assertThat(findUser.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(findUser.getPassword()).isEqualTo("password");
    }
}
