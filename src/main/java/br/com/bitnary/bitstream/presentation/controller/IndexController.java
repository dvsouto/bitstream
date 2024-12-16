package br.com.bitnary.bitstream.presentation.controller;

import br.com.bitnary.bitstream.presentation.response.ApiResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
public class IndexController {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response index() {
        return new ApiResponse().success()
                .setMessage("Server online")
                .response();
    }
}
