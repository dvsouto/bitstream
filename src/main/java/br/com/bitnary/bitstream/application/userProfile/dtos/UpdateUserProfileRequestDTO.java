package br.com.bitnary.bitstream.application.userProfile.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserProfileRequestDTO {
    @JsonProperty("name")
    @NotBlank(message = "Campo \"name\" é obrigatório")
    private String name;
}
