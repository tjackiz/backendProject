package de.tjackiz.gatewayService.exception.handler;

import de.tjackiz.gatewayService.exception.model.ApiError;
import de.tjackiz.gatewayService.exception.model.ApiSubError;
import de.tjackiz.gatewayService.exception.model.ApiValidationError;
import jakarta.validation.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String error = "Malformed JSON request LO)L";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        String error = "Schema validation failed";
        List<ApiSubError> apiSubErrorList = new ArrayList<>();


        ex.getConstraintViolations()
                .forEach(constraintViolation -> {

                    String constraintViolationPath = constraintViolation.getPropertyPath().toString();
                    String object = constraintViolation.getLeafBean().toString();
                    String message = constraintViolationPath.split("\\.", 2)[1] + " " + constraintViolation.getMessage();
                    Object rejectedValue = constraintViolation.getInvalidValue();
                    String[] constraintViolationPathArr = constraintViolationPath.split("\\.");
                    String field = constraintViolationPathArr[constraintViolationPathArr.length - 1];

                    apiSubErrorList.add(new ApiValidationError(object, message, rejectedValue, field));
                });

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, error, ex);
        apiError.setSubErrors(apiSubErrorList);

        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    //other exception handlers below

}
