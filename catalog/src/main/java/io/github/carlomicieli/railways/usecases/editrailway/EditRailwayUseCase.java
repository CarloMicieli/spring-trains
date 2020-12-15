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
package io.github.carlomicieli.railways.usecases.editrailway;

import io.github.carlomicieli.usecases.AbstractUseCase;
import io.github.carlomicieli.usecases.boundaries.input.UseCaseInputValidator;

public class EditRailwayUseCase
    extends AbstractUseCase<EditRailwayInput, EditRailwayOutput, EditRailwayOutputPort> {
  public EditRailwayUseCase(
      UseCaseInputValidator<EditRailwayInput> inputValidator, EditRailwayOutputPort outputPort) {
    super(inputValidator, outputPort);
  }

  @Override
  protected void handle(EditRailwayInput input) {
    throw new UnsupportedOperationException("TODO");
  }
}