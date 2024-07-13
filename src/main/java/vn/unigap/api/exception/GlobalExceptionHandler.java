package vn.unigap.api.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import vn.unigap.api.dto.out.CustomResponse;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    // handle valid Exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        FieldError errors = ex.getBindingResult().getFieldError();
        assert errors != null;
        String errorMessage = errors.getDefaultMessage();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>(3,
                HttpStatus.BAD_REQUEST.value(), errorMessage, ""));
    }

    // handle NotFoundException
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex) {
        String errorMessage = ex.getMessage();

        log.warn(errorMessage);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new CustomResponse<>(1, HttpStatus.NOT_FOUND.value(), errorMessage, ""));
    }

    // handle DuplicateKeyException
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<?> handleDuplicateKeyException(DuplicateKeyException ex) {
        String errorMessage = ex.getMessage();

        log.warn(errorMessage);

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new CustomResponse<>(2, HttpStatus.CONFLICT.value(), errorMessage, ""));
    }

    // handle Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        String errorMessage = ex.getMessage();

        log.warn(errorMessage);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new CustomResponse<>(5, HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage, ""));
    }
}
