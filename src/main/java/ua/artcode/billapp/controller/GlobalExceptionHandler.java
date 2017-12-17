package ua.artcode.billapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.artcode.billapp.exception.BillApplicationException;

/**
 * Created by Serhii Kolomiiets on 17.12.2017.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Unexpected error!")
    public @ResponseBody String handleThrowable(Throwable throwable) {
        return throwable.toString();
    }

    @ExceptionHandler(BillApplicationException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Unexpected error in application!")
    public @ResponseBody String handleBillApplicationException(BillApplicationException exception) {
        return exception.toString();
    }
}
