package br.com.bitnary.bitstream.presentation.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.ws.rs.core.Response;

import java.lang.reflect.Field;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIncludeProperties({ "success", "message", "data" })
public class ApiResponse {
    private Boolean success;
    private String message;
    private Object data = null;

    @JsonIgnore()
    private Response.Status httpStatus;

    public ApiResponse(Boolean success, String message, Response.Status httpStatus, Object data) {
        this.success = success;
        this.message = message;
        this.httpStatus = httpStatus;
        this.data = data;
    }

    public ApiResponse(Boolean success, String message, Response.Status httpStatus) {
        this(success, message, httpStatus, null);
    }

    public ApiResponse(Boolean success, String message) {
        this(success, message, Response.Status.OK, null);
    }

    public ApiResponse(String message) {
        this(true, message, Response.Status.OK, null);
    }


    public ApiResponse() {
        this(true, "ok", Response.Status.OK, null);
    }

    public ApiResponse success(Response.Status httpStatus) {
        this.success = true;
        this.httpStatus = httpStatus;

        return this;
    }

    public ApiResponse success() {
        return this.success(Response.Status.OK);
    }

    public ApiResponse fail(Response.Status httpStatus) {
        this.success = false;
        this.httpStatus = httpStatus;

        return this;
    }

    public ApiResponse fail() {
        return this.fail(Response.Status.BAD_REQUEST);
    }

    public Response response() {
        return Response.status(this.httpStatus)
                .entity(this)
                .build();
    }

    /// //////////

    public boolean isSuccess() {
        return success;
    }

    public ApiResponse setSuccess(boolean success) {
        this.success = success;

        return this;
    }

    public String getMessage() {
        return message;
    }

    public ApiResponse setMessage(String message) {
        this.message = message;

        return this;
    }

    public ApiResponse setData(Object data) {
        this.data = data;

        return this;
    }

    public Object getData() {
        return this.data;
    }

    public ApiResponse setHttpStatus(Response.Status httpStatus) {
        this.httpStatus = httpStatus;

        return this;
    }
}
