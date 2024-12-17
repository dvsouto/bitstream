package br.com.bitnary.bitstream.application.user.dtos;

public record UserResponseDTO (
        String name,
        String email,
        boolean active
) { }