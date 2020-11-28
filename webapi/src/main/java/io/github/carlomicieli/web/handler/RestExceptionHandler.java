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
package io.github.carlomicieli.web.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j2
public class RestExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ProblemDetail> handleValidationExceptions(
      MethodArgumentNotValidException ex) {

    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            (error) -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });

    UUID id = UUID.randomUUID();
    log.error("\n\terror_id={}\n\t{}", id, ex);

    ProblemDetail problemDetail =
        ProblemDetail.builder()
            .type(URN.fromProblemType("bad-request"))
            .title("Invalid request")
            .detail("Fields validation failed for this request. Check them before you try again.")
            .status(HttpStatus.BAD_REQUEST.value())
            .instance(URN.fromUUID(id))
            .fields(errors)
            .build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .contentType(ProblemDetail.JSON_MEDIA_TYPE)
        .body(problemDetail);
  }
}
