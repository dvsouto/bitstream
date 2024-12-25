package br.com.bitnary.bitstream.infrastructure.repositories.implementations;

import br.com.bitnary.bitstream.application.core.mappers.MediaMapper;
import br.com.bitnary.bitstream.application.core.mappers.UserMapper;
import br.com.bitnary.bitstream.domain.media.Media;
import br.com.bitnary.bitstream.domain.media.MediaRepository;
import br.com.bitnary.bitstream.infrastructure.entities.UserEntity;
import br.com.bitnary.bitstream.infrastructure.entities.UserMediaEntity;
import br.com.bitnary.bitstream.infrastructure.repositories.jpa.MediaRepositoryJPA;
import br.com.bitnary.bitstream.infrastructure.repositories.jpa.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class MediaRepositoryImpl implements MediaRepository {
    @Autowired
    MediaRepositoryJPA mediaRepositoryJPA;

    @Autowired
    MediaMapper mediaMapper;

    @Override
    public List<Media> findAll() {
        List<UserMediaEntity> list = mediaRepositoryJPA.findAll();

        return this.mapList(list, mediaMapper::toDomain);
    }

    @Override
    public Optional<Media> findById(String id) {
        Optional<UserMediaEntity> entityRaw = mediaRepositoryJPA.findById(UUID.fromString(id));

        return this.mapObject(entityRaw, mediaMapper::toDomain);
    }

    @Override
    public List<Media> findByUserId(String userId) {
        List<UserMediaEntity> list = mediaRepositoryJPA.findByUserId(UUID.fromString(userId));

        return this.mapList(list, mediaMapper::toDomain);
    }

    @Override
    public List<Media> findByUserProfileId(String userProfileId) {
        List<UserMediaEntity> list = mediaRepositoryJPA.findByUserProfileId(UUID.fromString(userProfileId));

        return this.mapList(list, mediaMapper::toDomain);
    }

    @Override
    public Media save(String userId, String userProfileId, Media media) {
        return null;
    }
}
