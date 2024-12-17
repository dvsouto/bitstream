package br.com.bitnary.bitstream.application.user.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequestDTO {
    @JsonProperty("email")
    @NotBlank(message = "Campo \"email\" é obrigatório")
    private String email;

    @JsonProperty("password")
    @NotBlank(message = "Campo \"password\" é obrigatório")
    private String password;

    @JsonProperty("name")
    @NotBlank(message = "Campo \"name\" é obrigatório")
    private String name;
}
