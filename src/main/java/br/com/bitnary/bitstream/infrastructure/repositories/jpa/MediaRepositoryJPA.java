package br.com.bitnary.bitstream.infrastructure.repositories.jpa;

import br.com.bitnary.bitstream.infrastructure.entities.UserEntity;
import br.com.bitnary.bitstream.infrastructure.entities.UserMediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MediaRepositoryJPA extends JpaRepository<UserMediaEntity, String> {
    Optional<UserMediaEntity> findById(UUID id);
    List<UserMediaEntity> findByUserId(UUID userId);
    List<UserMediaEntity> findByUserProfileId(UUID userProfileId);

    UserMediaEntity save(UserMediaEntity userEntity);
}
