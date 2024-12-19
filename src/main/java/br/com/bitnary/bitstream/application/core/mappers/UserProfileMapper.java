package br.com.bitnary.bitstream.application.core.mappers;

import br.com.bitnary.bitstream.domain.userProfile.UserProfile;
import br.com.bitnary.bitstream.infrastructure.entities.UserProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    @Mapping(target = "user.roles", ignore = true)
    UserProfile toDomain(UserProfileEntity user);

    @Mapping(target = "user.profiles", ignore = true)
    UserProfileEntity toEntity(UserProfile user);
}
