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
package io.github.carlomicieli.web.presenters;

import io.github.carlomicieli.usecases.boundaries.output.UseCaseOutput;
import io.github.carlomicieli.usecases.boundaries.output.port.StandardOutputPort;
import io.github.carlomicieli.validation.ValidationError;
import io.github.carlomicieli.web.problems.ProblemDetail;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Log4j2
public abstract class ServerResponseOutputPort<T extends UseCaseOutput>
    implements StandardOutputPort<T> {

  private ResponseEntity<?> response;

  protected void setResponse(ResponseEntity<?> response) {
    this.response = Objects.requireNonNull(response);
  }

  public ResponseEntity<?> getResponse() {
    return response;
  }

  @Override
  public void error(String errorMessage) {
    log.error(errorMessage);

    setResponse(
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .contentType(ProblemDetail.JSON_MEDIA_TYPE)
            .body(ProblemDetail.error(errorMessage)));
  }

  @Override
  public void error(Throwable ex) {
    log.error(ex);

    setResponse(
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .contentType(ProblemDetail.JSON_MEDIA_TYPE)
            .body(ProblemDetail.error(ex)));
  }

  @Override
  public void invalidRequest(List<ValidationError> validationErrors) {
    Map<String, String> errors =
        validationErrors.stream()
            .collect(
                Collectors.toMap(
                    ValidationError::getPropertyName, ValidationError::getErrorMessage));
    setResponse(
        ResponseEntity.badRequest()
            .contentType(ProblemDetail.JSON_MEDIA_TYPE)
            .body(ProblemDetail.invalidRequest(errors)));
  }
}
