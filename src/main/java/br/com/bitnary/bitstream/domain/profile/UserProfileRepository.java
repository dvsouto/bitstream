package br.com.bitnary.bitstream.domain.profile;

import br.com.bitnary.bitstream.domain.core.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface UserProfileRepository extends Repository<UserProfile> {
    UserProfile save(String userId, UserProfile userProfile);
    Optional<UserProfile> findById(String id);
    List<UserProfile> findByUserId(String userId);
}
