package com.formento.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EcommerceExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EcommerceExceptionDefault.class)
    @ResponseBody
    public EcommerceExceptionMessage handleBadRequest(EcommerceException e) {
        return e.buildEcommerceExceptionMessage();
    }

    // 401 Unauthorized:
    // If the request already included Authorization credentials, then the 401 response indicates that authorization has been refused for those credentials.
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedEcommerceException.class)
    @ResponseBody
    public EcommerceExceptionMessage handleUnauthorized(UnauthorizedEcommerceException e) {
        return e.buildEcommerceExceptionMessage();
    }

    // 403 Forbidden:
    // The server understood the request, but is refusing to fulfill it.
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedEcommerceException.class)
    @ResponseBody
    public EcommerceExceptionMessage handleForbidden(AccessDeniedEcommerceException e) {
        return e.buildEcommerceExceptionMessage();
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(BusinessEcommerceException.class)
    @ResponseBody
    public EcommerceExceptionMessage handleBadRequest(BusinessEcommerceException e) {
        return e.buildEcommerceExceptionMessage();
    }

}