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
package io.github.carlomicieli.web.problems;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

// From RFC-7807
// "problem detail" is a way to carry machine-readable details of errors in a HTTP response to avoid
// the need to
//        define new error response formats for HTTP APIs.
@Data
@Builder
public class ProblemDetail {
  public static final MediaType JSON_MEDIA_TYPE = MediaType.valueOf("application/problem+json");

  private URN type;
  private String title;
  private String detail;
  private Integer status;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime timestamp;

  private URN instance;
  private Map<String, String> fields;

  public static ProblemDetail unprocessableEntity(String message) {
    UUID id = UUID.randomUUID();
    return ProblemDetail.builder()
        .timestamp(LocalDateTime.now())
        .type(URN.fromProblemType("unprocessable-entity"))
        .title("Unprocessable entity")
        .detail(message)
        .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
        .instance(URN.fromUUID(id))
        .build();
  }

  public static ProblemDetail error(String error) {
    UUID id = UUID.randomUUID();
    return ProblemDetail.builder()
        .timestamp(LocalDateTime.now())
        .type(URN.fromProblemType("internal-server-error"))
        .title("Internal Server Error")
        .detail(error)
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .instance(URN.fromUUID(id))
        .build();
  }

  public static ProblemDetail error(Throwable ex) {
    return error("Oops, something went wrong");
  }

  public static ProblemDetail invalidRequest(Map<String, String> errors) {
    UUID id = UUID.randomUUID();
    return ProblemDetail.builder()
        .timestamp(LocalDateTime.now())
        .type(URN.fromProblemType("bad-request"))
        .title("Invalid request")
        .detail("Fields validation failed for this request. Check them before you try again.")
        .status(HttpStatus.BAD_REQUEST.value())
        .instance(URN.fromUUID(id))
        .fields(errors)
        .build();
  }
}
