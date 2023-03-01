package com.aduilio.med.problemdetails;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.aduilio.med.exception.ServiceException;

/**
 * To enable RFC 7807 error responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String CONSTRAINT_VIOLATION = "Constraint violation.";
    private static final String VIOLATIONS = "violations";

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ProblemDetail problem = exception.getBody();

        List<ConstraintViolation> violations = exception
                .getBindingResult().getFieldErrors().stream().map(fieldError -> ConstraintViolation.builder()
                        .field(fieldError.getField()).message(fieldError.getDefaultMessage()).build())
                .collect(Collectors.toList());

        problem.setProperty(VIOLATIONS, violations);
        problem.setTitle(problem.getDetail());
        problem.setDetail(CONSTRAINT_VIOLATION);

        return new ResponseEntity<>(exception.getBody(), headers, status);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Object> handleRuntimeException(ServiceException exception) {
        var problemDetails = ProblemDetail.forStatusAndDetail(exception.getStatus(), exception.getMessage());
        return new ResponseEntity<>(problemDetails, exception.getStatus());
    }
}
