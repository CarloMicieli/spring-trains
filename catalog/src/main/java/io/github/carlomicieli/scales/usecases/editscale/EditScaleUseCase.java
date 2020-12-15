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
package io.github.carlomicieli.scales.usecases.editscale;

import io.github.carlomicieli.usecases.AbstractUseCase;
import io.github.carlomicieli.usecases.boundaries.input.UseCaseInputValidator;

public class EditScaleUseCase
    extends AbstractUseCase<EditScaleInput, EditScaleOutput, EditScaleOutputPort> {
  public EditScaleUseCase(
      UseCaseInputValidator<EditScaleInput> inputValidator, EditScaleOutputPort outputPort) {
    super(inputValidator, outputPort);
  }

  @Override
  protected void handle(EditScaleInput input) {
    throw new UnsupportedOperationException("TODO");
  }
}
