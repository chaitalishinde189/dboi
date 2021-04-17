package com.dboi.trademanagement.business;

import com.dboi.trademanagement.model.BadRequestException;
import com.dboi.trademanagement.model.ExceptionResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class ExceptionMapper {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse badRequestException(BadRequestException e) {
        return new ExceptionResponse(BAD_REQUEST.getReasonPhrase(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExceptionResponse generalException(Exception e) {
        return new ExceptionResponse(INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage());
    }
}
