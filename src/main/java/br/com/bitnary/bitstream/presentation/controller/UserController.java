package br.com.bitnary.bitstream.presentation.controller;

import br.com.bitnary.bitstream.application.auth.dtos.AuthenticateUserRequestDTO;
import br.com.bitnary.bitstream.application.core.mappers.UserMapper;
import br.com.bitnary.bitstream.application.auth.usecases.AuthenticateUserUseCase;
import br.com.bitnary.bitstream.application.user.dtos.CreateUserRequestDTO;
import br.com.bitnary.bitstream.application.user.usecases.CreateUserUseCase;
import br.com.bitnary.bitstream.domain.user.User;
import br.com.bitnary.bitstream.presentation.response.ApiResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
@Path("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CreateUserUseCase createUserUseCase;

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Valid @NotNull CreateUserRequestDTO request) {
        User user = createUserUseCase.execute(userMapper.toDomain(request));

        return new ApiResponse()
                .success()
                .setData(userMapper.toDefaultResponse(user))
                .response();
    }
}
