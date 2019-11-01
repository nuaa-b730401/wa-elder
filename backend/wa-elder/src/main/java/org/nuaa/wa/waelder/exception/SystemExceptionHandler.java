package org.nuaa.wa.waelder.exception;

import org.nuaa.wa.waelder.entity.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: ToMax
 * @Description:
 * @Date: Created in 2018/12/3 21:40
 */
@RestControllerAdvice
public class SystemExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public Response handle(NullPointerException e) {
        return new Response(
                Response.NORMAL_ERROR_CODE,
                e.getMessage()
        );
    }

    @ExceptionHandler({UnsupportedEncodingException.class, NoSuchAlgorithmException.class})
    public Response handle(Exception e) {
        return new Response(
                Response.SERVER_ERROR_CODE,
                e.getMessage()
        );
    }

    @ExceptionHandler(IOException.class)
    public Response handle(IOException e) {
        return new Response(
                Response.SERVER_FILE_SYSTEM_ERROR, e.getMessage()
        );
    }

    @ExceptionHandler(PermissionException.class)
    public Response handle(PermissionException e) {
        return new Response(
                Response.PERMISSION_DENIED_CODE, e.getMessage()
        );
    }
}
