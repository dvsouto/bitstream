package br.com.bitnary.bitstream.application.core.mappers;

import br.com.bitnary.bitstream.application.profile.dtos.CreateUserProfileRequestDTO;
import br.com.bitnary.bitstream.application.profile.dtos.UpdateUserProfileRequestDTO;
import br.com.bitnary.bitstream.application.profile.dtos.UserProfileResponseDTO;
import br.com.bitnary.bitstream.domain.profile.UserProfile;
import br.com.bitnary.bitstream.infrastructure.entities.UserProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    @Mapping(target = "user.roles", ignore = true)
    UserProfile toDomain(UserProfileEntity userProfile);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    UserProfile toDomain(CreateUserProfileRequestDTO userProfile);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    UserProfile toDomain(UpdateUserProfileRequestDTO userProfile);

    @Mapping(target = "user.profiles", ignore = true)
    UserProfileEntity toEntity(UserProfile user);

    UserProfileResponseDTO toDefaultResponse(UserProfile userProfile);
}
