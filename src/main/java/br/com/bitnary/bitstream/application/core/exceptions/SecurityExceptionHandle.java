//package br.com.bitnary.bitstream.application.core.exceptions;
//
//import br.com.bitnary.bitstream.presentation.response.ApiResponse;
//import jakarta.ws.rs.core.Response;
//import jakarta.ws.rs.ext.ExceptionMapper;
//import jakarta.ws.rs.ext.Provider;
//
//@Provider
//public class SecurityExceptionHandle implements ExceptionMapper<SecurityException> {
//
//    @Override
//    public Response toResponse(SecurityException exception) {
//        return new ApiResponse()
//                .fail()
//                .setHttpStatus(Response.Status.UNAUTHORIZED)
//                .setMessage("teste")
//                .response();
//    }
//}