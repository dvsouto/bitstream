package br.com.bitnary.bitstream.infrastructure.repositories.jpa;

import br.com.bitnary.bitstream.infrastructure.entities.UserEntity;
import br.com.bitnary.bitstream.infrastructure.entities.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserProfileRepositoryJPA extends JpaRepository<UserProfileEntity, String> {
    Optional<UserProfileEntity> findById(UUID id);
    List<UserProfileEntity> findByUser(UserEntity user);

    UserProfileEntity save(UserProfileEntity userProfileEntity);
}