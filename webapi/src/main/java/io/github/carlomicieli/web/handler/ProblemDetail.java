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

import java.util.Map;
import lombok.Builder;
import lombok.Data;
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
  private URN instance;
  private Map<String, String> fields;
}
