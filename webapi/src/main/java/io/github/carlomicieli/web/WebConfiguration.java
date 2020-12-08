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
package io.github.carlomicieli.web;

import io.github.carlomicieli.usecases.boundaries.input.UseCaseBeanValidator;
import io.github.carlomicieli.usecases.boundaries.input.UseCaseInput;
import io.github.carlomicieli.usecases.boundaries.input.UseCaseInputValidator;
import java.time.Clock;
import javax.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {
  @Bean
  public <T extends UseCaseInput> UseCaseInputValidator<T> useCaseInputValidator(
      Validator validator) {
    return new UseCaseBeanValidator<>(validator);
  }

  @Bean
  public Clock clock() {
    return Clock.systemUTC();
  }
}
