package br.com.bitnary.bitstream.infrastructure.repositories.implementations;

import br.com.bitnary.bitstream.application.core.mappers.UserMapper;
import br.com.bitnary.bitstream.domain.user.User;
import br.com.bitnary.bitstream.domain.user.UserRepository;
import br.com.bitnary.bitstream.infrastructure.entities.UserEntity;
import br.com.bitnary.bitstream.infrastructure.repositories.jpa.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    UserRepositoryJPA userRepositoryJPA;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userRepositoryJPA
                .findAll()
                .stream()
                .map(userEntity -> userMapper.toDomain(userEntity))
                .toList();
    }

    @Override
    public Optional<User> findById(String id) {
        Optional<UserEntity> userJpa = userRepositoryJPA.findById(UUID.fromString(id));

        return userJpa.map(userEntity -> userMapper.toDomain(userEntity));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        email = email.trim().toLowerCase();

        Optional<UserEntity> userJpa = userRepositoryJPA.findByEmail(email);

        return userJpa.map(userEntity -> userMapper.toDomain(userEntity));
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        userEntity.setEmail(userEntity.getEmail().trim().toLowerCase());
        userEntity = userRepositoryJPA.save(userEntity);

        return userMapper.toDomain(userEntity);
    }

    @Override
    public Optional<User> authenticate(String email, String password) {
        email = email.trim().toLowerCase();

        Optional<UserEntity> userJpa = userRepositoryJPA.findByEmailAndPassword(email, password);

        return userJpa.map(userEntity -> userMapper.toDomain(userEntity));
    }
}
