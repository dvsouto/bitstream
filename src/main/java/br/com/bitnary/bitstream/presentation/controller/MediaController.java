package br.com.bitnary.bitstream.presentation.controller;

import br.com.bitnary.bitstream.application.auth.usecases.GetAuthenticatedUserUseCase;
import br.com.bitnary.bitstream.application.core.mappers.MediaMapper;
import br.com.bitnary.bitstream.application.media.dtos.MediaResponseDTO;
import br.com.bitnary.bitstream.application.media.exceptions.MediaNotFoundException;
import br.com.bitnary.bitstream.application.media.usecases.GetMediaByIdUseCase;
import br.com.bitnary.bitstream.application.media.usecases.ListMediaUseCase;
import br.com.bitnary.bitstream.domain.media.Media;
import br.com.bitnary.bitstream.domain.profile.UserProfile;
import br.com.bitnary.bitstream.domain.user.User;
import br.com.bitnary.bitstream.presentation.response.ApiResponse;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Path("/media")
public class MediaController {
    @Autowired
    GetAuthenticatedUserUseCase getAuthenticatedUserUseCase;

    @Autowired
    ListMediaUseCase listMediaUseCase;

    @Autowired
    GetMediaByIdUseCase getMediaByIdUseCase;

    @Autowired
    MediaMapper mediaMapper;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        User user = getAuthenticatedUserUseCase.execute().orElseThrow();
        List<Media> medias = listMediaUseCase.execute(user.getId());

        List<MediaResponseDTO> response = medias.stream()
                .map(media -> mediaMapper.toDefaultResponse(media))
                .toList();

        return new ApiResponse()
                .success()
                .setData(response)
                .response();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@NotNull @PathParam("id") String mediaId) throws MediaNotFoundException {
        Media media = getMediaByIdUseCase.execute(mediaId);

        return new ApiResponse()
                .success()
                .setData(mediaMapper.toDefaultResponse(media))
                .response();
    }
}
