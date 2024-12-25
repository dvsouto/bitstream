package br.com.bitnary.bitstream.domain.media;

import br.com.bitnary.bitstream.domain.core.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface MediaRepository extends Repository<Media> {
    Optional<Media> findById(String id);
    List<Media> findByUserId(String userId);
    List<Media> findByUserProfileId(String userProfileId);

    Media save(String userId, String userProfileId, Media media);
}
