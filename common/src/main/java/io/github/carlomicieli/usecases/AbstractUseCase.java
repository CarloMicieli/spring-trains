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
package io.github.carlomicieli.usecases;

import io.github.carlomicieli.usecases.boundaries.input.UseCaseInput;
import io.github.carlomicieli.usecases.boundaries.input.UseCaseInputValidator;
import io.github.carlomicieli.usecases.boundaries.output.UseCaseOutput;
import io.github.carlomicieli.usecases.boundaries.output.port.StandardOutputPort;
import io.github.carlomicieli.validation.ValidationError;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
public abstract class AbstractUseCase<
        InType extends UseCaseInput,
        OutType extends UseCaseOutput,
        OutPortType extends StandardOutputPort<OutType>>
    implements UseCase<InType> {

  protected UseCaseInputValidator<InType> inputValidator;
  protected OutPortType outputPort;

  @Override
  public void execute(InType input) {
    if (input == null) {
      outputPort.error("The use case input is null");
      return;
    }

    List<ValidationError> validationErrors = inputValidator.validateInput(input);
    if (!validationErrors.isEmpty()) {
      outputPort.invalidRequest(validationErrors);
      return;
    }

    handle(input);
  }

  /**
   * The {@code UseCase} handler method. This method must not throw exceptions, but return the error
   * message using the appropriate output port.
   */
  protected abstract void handle(InType input);
}
