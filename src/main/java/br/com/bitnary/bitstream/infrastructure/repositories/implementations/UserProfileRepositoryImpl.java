package br.com.bitnary.bitstream.infrastructure.repositories.implementations;

import br.com.bitnary.bitstream.application.core.mappers.UserMapper;
import br.com.bitnary.bitstream.application.core.mappers.UserProfileMapper;
import br.com.bitnary.bitstream.domain.userProfile.UserProfile;
import br.com.bitnary.bitstream.domain.userProfile.UserProfileRepository;
import br.com.bitnary.bitstream.infrastructure.entities.UserEntity;
import br.com.bitnary.bitstream.infrastructure.entities.UserProfileEntity;
import br.com.bitnary.bitstream.infrastructure.repositories.jpa.UserProfileRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserProfileRepositoryImpl implements UserProfileRepository {
    @Autowired
    UserProfileRepositoryJPA userProfileRepositoryJPA;

    @Autowired
    UserProfileMapper userProfileMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserProfile> findAll() {
        return userProfileRepositoryJPA
                .findAll()
                .stream()
                .map(entity -> userProfileMapper.toDomain(entity))
                .toList();
    }

    @Override
    public Optional<UserProfile> findById(String id) {
        Optional<UserProfileEntity> userJpa = userProfileRepositoryJPA.findById(UUID.fromString(id));

        return userJpa.map(userEntity -> userProfileMapper.toDomain(userEntity));
    }

    @Override
    public List<UserProfile> findByUserId(String id) {
        UserEntity user = new UserEntity();
        user.setId(UUID.fromString(id));

        List<UserProfileEntity> profiles = userProfileRepositoryJPA.findByUser(user);

        return profiles
                .stream()
                .map(userProfileEntity -> userProfileMapper.toDomain(userProfileEntity))
                .toList();
    }

    @Override
    public UserProfile save(String userId, UserProfile userProfile) {
        UserEntity user = new UserEntity();
        user.setId(UUID.fromString(userId));

        UserProfileEntity userProfileEntity = userProfileMapper.toEntity(userProfile);
        userProfileEntity.setUser(user);
        userProfileEntity = userProfileRepositoryJPA.save(userProfileEntity);

        return userProfileMapper.toDomain(userProfileEntity);
    }
}
