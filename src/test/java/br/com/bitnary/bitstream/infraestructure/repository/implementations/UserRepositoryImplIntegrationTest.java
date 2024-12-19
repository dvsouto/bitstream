package br.com.bitnary.bitstream.infraestructure.repository.implementations;

import br.com.bitnary.bitstream.application.core.mappers.UserMapper;
import br.com.bitnary.bitstream.domain.user.User;
import br.com.bitnary.bitstream.infrastructure.entities.UserEntity;
import br.com.bitnary.bitstream.infrastructure.repositories.implementations.UserRepositoryImpl;
import br.com.bitnary.bitstream.infrastructure.repositories.jpa.UserRepositoryJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@Transactional
public class UserRepositoryImplIntegrationTest {
    @Autowired
    UserRepositoryJPA userRepositoryJPA;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRepositoryImpl userRepositoryImpl;

    User user;
    UserEntity userEntity;

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .password("password")
                .build();

        userEntity = userMapper.toEntity(user);

        userRepositoryJPA.save(userEntity);
    }

    @Test
    void testSaveUserIntegration() {
        User savedUser = userRepositoryImpl.save(user);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getName()).isEqualTo("John Doe");
        assertThat(savedUser.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(savedUser.getPassword()).isEqualTo("password");
    }

    @Test
    void testFindUserByIdIntegration() {
        UserEntity savedUser = userRepositoryJPA.save(userEntity);

        userRepositoryJPA.flush();

        User foundUser = userRepositoryImpl.findById(savedUser.getId().toString()).orElse(null);

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isEqualTo(savedUser.getId().toString());
    }

    @Test
    void testFindUserByEmailIntegration() {
        User foundUser = userRepositoryImpl.findByEmail("john.doe@example.com").orElse(null);

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo("john.doe@example.com");
    }

    @Test
    void testFindAllUsersIntegration() {
        List<User> users = userRepositoryImpl.findAll();

        assertThat(users.size()).isGreaterThanOrEqualTo(1);
    }
}
