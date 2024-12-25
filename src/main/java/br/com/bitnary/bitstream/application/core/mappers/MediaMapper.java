package br.com.bitnary.bitstream.application.core.mappers;

import br.com.bitnary.bitstream.application.media.dtos.MediaResponseDTO;
import br.com.bitnary.bitstream.domain.media.Media;
import br.com.bitnary.bitstream.infrastructure.entities.UserMediaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MediaMapper {
    Media toDomain(UserMediaEntity media);

    UserMediaEntity toEntity(Media media);

    MediaResponseDTO toDefaultResponse(Media media);
}
