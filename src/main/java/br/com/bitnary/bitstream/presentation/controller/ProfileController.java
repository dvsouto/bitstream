package br.com.bitnary.bitstream.presentation.controller;

import br.com.bitnary.bitstream.application.core.mappers.UserMapper;
import br.com.bitnary.bitstream.application.core.mappers.UserProfileMapper;
import br.com.bitnary.bitstream.application.user.dtos.CreateUserRequestDTO;
import br.com.bitnary.bitstream.application.user.usecases.CreateUserUseCase;
import br.com.bitnary.bitstream.application.userProfile.dtos.CreateUserProfileRequestDTO;
import br.com.bitnary.bitstream.application.userProfile.dtos.UpdateUserProfileRequestDTO;
import br.com.bitnary.bitstream.application.userProfile.usecases.CreateUserProfileUseCase;
import br.com.bitnary.bitstream.application.userProfile.usecases.FindUserProfileByIdUseCase;
import br.com.bitnary.bitstream.application.userProfile.usecases.UpdateUserProfileUseCase;
import br.com.bitnary.bitstream.domain.user.User;
import br.com.bitnary.bitstream.domain.userProfile.UserProfile;
import br.com.bitnary.bitstream.presentation.response.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Path("/user/profile")
public class ProfileController {
    @Autowired
    private UserProfileMapper userProfileMapper;

    @Autowired
    private CreateUserProfileUseCase createUserProfileUseCase;

    @Autowired
    private UpdateUserProfileUseCase updateUserProfileUseCase;

    @Autowired
    private FindUserProfileByIdUseCase findUserProfileByIdUseCase;

    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid @NotNull CreateUserProfileRequestDTO request) {
        UserProfile userProfile = createUserProfileUseCase.execute(userProfileMapper.toDomain(request));

        return new ApiResponse()
                .success()
                .setData(userProfileMapper.toDefaultResponse(userProfile))
                .response();
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@NotNull @PathParam("id") String userProfileId) {
        UserProfile userProfile = findUserProfileByIdUseCase.execute(userProfileId);

        return new ApiResponse()
                .success()
                .setData(userProfileMapper.toDefaultResponse(userProfile))
                .response();
    }

    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@NotNull @PathParam("id") String userProfileId, @Valid @NotNull UpdateUserProfileRequestDTO request) {
        UserProfile databaseUserProfile = findUserProfileByIdUseCase.execute(userProfileId);
        UserProfile updateUserProfile = userProfileMapper.toDomain(request);

        updateUserProfile.setId(databaseUserProfile.getId());
        updateUserProfile.setUser(databaseUserProfile.getUser());

        UserProfile savedUserProfile = this.updateUserProfileUseCase.execute(databaseUserProfile.getId(), updateUserProfile);

        return new ApiResponse()
                .success()
                .setData(userProfileMapper.toDefaultResponse(savedUserProfile))
                .response();
    }
}
