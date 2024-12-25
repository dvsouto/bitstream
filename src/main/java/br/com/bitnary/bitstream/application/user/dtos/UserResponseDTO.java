package br.com.bitnary.bitstream.application.user.dtos;

import java.util.List;

public record UserResponseDTO (
        String name,
        String email,
        boolean active,
        List<UserProfileRecord> profiles
) {
    public record UserProfileRecord (
            String name
    ) { }
}