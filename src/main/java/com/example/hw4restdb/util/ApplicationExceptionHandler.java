package com.example.hw4restdb.util;

import com.example.hw4restdb.util.exceptions.ApplicationRestException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

  /**
   * Exception handler for all exceptions extends
   *
   * @param ex ApplicationRestException exception
   * @return map with accumulated in {@code errorMessage} key errors
   */
  @ExceptionHandler({ApplicationRestException.class})
  public ResponseEntity<?> applicationRestException(ApplicationRestException ex) {
    return ResponseEntity.status(ex.getHttpStatus())
        .body(Map.of("ErrorCode", ex.getErrorCode(), "ErrorMessage", ex.getErrorMessage()));

  }

  /**
   * Exception handler for all SQL exceptions (when occur when user delete, update entity)
   *
   * @param ex SQLException exception
   * @return map with accumulated in {@code errorMessage} key errors
   */
  @ExceptionHandler({SQLException.class})
  public ResponseEntity<?> applicationRestException(SQLException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(Map.of("ErrorCode", ex.getErrorCode(), "ErrorMessage", ex.getMessage()));

  }

  /**
   * Exception handler for MethodArgumentNotValidException that raises when
   * annotation from {@code javax.validation.constraints} are used and corresponding checks
   * are failing. (For instance name in GoodsDto must be not empty)
   *
   * @param ex MethodArgumentNotValidException exception
   * @return map with accumulated in {@code errorMessage} key errors
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(Map.of("ErrorCode", "bad_request", "ErrorMessage", "Invalid fields: " + errors));
  }


}
