/*
   Copyright 2020 (C) Carlo Micieli

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package io.github.carlomicieli.web.controlleradvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import io.github.carlomicieli.web.problems.ProblemDetail;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import org.apache.logging.log4j.core.config.Order;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected @Nonnull ResponseEntity<Object> handleHttpMessageNotReadable(
      @Nonnull HttpMessageNotReadableException ex,
      @Nonnull HttpHeaders headers,
      @Nonnull HttpStatus status,
      @Nonnull WebRequest request) {

    String message = ex.getMessage();
    var innerEx = ex.getCause();
    if (innerEx instanceof InvalidFormatException) {
      var formatEx = (InvalidFormatException) innerEx;
      message = formatEx.getOriginalMessage();
    }

    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
        .contentType(ProblemDetail.JSON_MEDIA_TYPE)
        .body(ProblemDetail.unprocessableEntity(message));
  }

  @Override
  protected @Nonnull ResponseEntity<Object> handleMethodArgumentNotValid(
      @Nonnull MethodArgumentNotValidException ex,
      @Nonnull HttpHeaders headers,
      @Nonnull HttpStatus status,
      @Nonnull WebRequest request) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            (error) -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .contentType(ProblemDetail.JSON_MEDIA_TYPE)
        .body(ProblemDetail.invalidRequest(errors));
  }
}
