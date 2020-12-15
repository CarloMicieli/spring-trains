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
package io.github.carlomicieli.railways.usecases.createrailway;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.carlomicieli.usecases.boundaries.input.UseCaseBeanValidator;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Create railway use case")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CreateRailwayUseCaseTest {
  private final CreateRailwayOutputPort outputPort;
  private final CreateRailwayUseCase useCase;

  public CreateRailwayUseCaseTest(@Mock CreateRailwayOutputPort outputPort) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    var useCaseValidator = new UseCaseBeanValidator<CreateRailwayInput>(factory.getValidator());

    this.outputPort = outputPort;
    this.useCase = new CreateRailwayUseCase(useCaseValidator, outputPort);
  }

  @Test
  void implement_me() {
    assertThat(outputPort).isNotNull();
    assertThat(useCase).isNotNull();
  }
}
