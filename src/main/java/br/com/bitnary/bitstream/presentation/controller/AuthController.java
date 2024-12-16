package br.com.bitnary.bitstream.presentation.controller;

import br.com.bitnary.bitstream.application.auth.dtos.AuthenticateUserRequestDTO;
import br.com.bitnary.bitstream.application.auth.dtos.AuthenticatedUserResponseDTO;
import br.com.bitnary.bitstream.application.auth.mappers.UserMapper;
import br.com.bitnary.bitstream.application.auth.usecases.AuthenticateUserUseCase;
import br.com.bitnary.bitstream.presentation.response.ApiResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/auth")
public class AuthController {
    @Autowired
    private UserMapper userMapper;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Valid @NotNull AuthenticateUserRequestDTO request) {
        AuthenticateUserUseCase.AuthenticatedUser authenticatedUser = new AuthenticateUserUseCase().execute(request.getEmail(), request.getPassword());

        return new ApiResponse()
                .success()
                .setData(userMapper.toAuthenticatedUserResponse(authenticatedUser.user(), authenticatedUser.token()))
                .response();
    }

    @GET
    @Path("/me")
    @Produces(MediaType.APPLICATION_JSON)
    public Response me() {
        return new ApiResponse()
                .success()
                .response();
    }
}
