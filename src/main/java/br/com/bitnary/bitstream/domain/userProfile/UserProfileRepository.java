package br.com.bitnary.bitstream.domain.userProfile;

import br.com.bitnary.bitstream.domain.core.Repository;

import java.util.List;

public interface UserProfileRepository extends Repository<UserProfile> {
    UserProfile save(String userId, UserProfile userProfile);
    List<UserProfile> findByUserId(String userId);
}
