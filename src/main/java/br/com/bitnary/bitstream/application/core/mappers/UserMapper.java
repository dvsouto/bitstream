package br.com.bitnary.bitstream.application.core.mappers;

import br.com.bitnary.bitstream.application.auth.dtos.AuthenticatedUserResponseDTO;
import br.com.bitnary.bitstream.application.user.dtos.CreateUserRequestDTO;
import br.com.bitnary.bitstream.application.user.dtos.UserResponseDTO;
import br.com.bitnary.bitstream.domain.auth.BearerToken;
import br.com.bitnary.bitstream.domain.user.User;
import br.com.bitnary.bitstream.infrastructure.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "user.name", source = "user.name")
    @Mapping(target = "user.email", source = "user.email")
    @Mapping(target = "token.access_token", source = "token.token")
    @Mapping(target = "token.token_type", source = "token.type")
    @Mapping(target = "token.expires_at", source = "token.expires_at")
    AuthenticatedUserResponseDTO toAuthenticatedUserResponse(User user, BearerToken token);

    @Mapping(target = "roles", ignore = true)
    User toDomain(UserEntity user);

    @Mapping(target = "active", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "birthdayDate", ignore = true)
    User toDomain(CreateUserRequestDTO dto);

    UserEntity toEntity(User user);
    UserResponseDTO toDefaultResponse(User user);
}