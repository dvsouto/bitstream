package br.com.bitnary.bitstream.application.auth.mappers;

import br.com.bitnary.bitstream.application.auth.dtos.AuthenticatedUserResponseDTO;
import br.com.bitnary.bitstream.domain.auth.BearerToken;
import br.com.bitnary.bitstream.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "user.name", source = "user.name")
    @Mapping(target = "user.email", source = "user.email")
    @Mapping(target = "token.access_token", source = "token.token")
    @Mapping(target = "token.token_type", source = "token.type")
    @Mapping(target = "token.expires_at", constant = "2024-12-31T23:59:59Z") // Static example
    AuthenticatedUserResponseDTO toAuthenticatedUserResponse(User user, BearerToken token);
}
