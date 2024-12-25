package br.com.bitnary.bitstream.application.auth.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class AuthenticateUserRequestDTO {
    @JsonProperty("email")
    @NotBlank(message = "Campo \"email\" é obrigatório")
    private final String email;

    @JsonProperty("password")
    @NotBlank(message = "Campo \"password\" é obrigatório")
    private final String password;

    public AuthenticateUserRequestDTO() {
        this.email = null;
        this.password = null;
    }

    public AuthenticateUserRequestDTO(String email, String password) {
        this.email = email != null ? email.toLowerCase() : null;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
