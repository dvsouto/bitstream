package br.com.bitnary.bitstream.infrastructure.repositories.jpa;

import br.com.bitnary.bitstream.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepositoryJPA extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findById(UUID id);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByEmailAndPassword(String email, String password);

    UserEntity save(UserEntity userEntity);

}
