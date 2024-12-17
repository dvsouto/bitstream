package br.com.bitnary.bitstream.application.auth.dtos;

public record AuthenticatedUserResponseDTO(
        TokenRecord token,
        UserRecord user
) {
    public record TokenRecord (
            String access_token,
            String token_type,
            String expires_at
    ){}

    public record UserRecord (
            String name,
            String email
    ){}
}