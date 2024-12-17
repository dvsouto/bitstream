package br.com.bitnary.bitstream.presentation.controller;

import br.com.bitnary.bitstream.application.user.usecases.FindAllUsersUseCase;
import br.com.bitnary.bitstream.presentation.response.ApiResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Path("/")
public class IndexController {

    @Autowired
    FindAllUsersUseCase findAllUsersUseCase;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response index() {
        findAllUsersUseCase.execute();

        return new ApiResponse().success()
                .setMessage("Server online")
                .response();
    }
}
