package br.com.bitnary.bitstream.application.media.dtos;

import java.util.List;

public record MediaResponseDTO (
        String id,
        String type,
        String name,
        String path
) {
}