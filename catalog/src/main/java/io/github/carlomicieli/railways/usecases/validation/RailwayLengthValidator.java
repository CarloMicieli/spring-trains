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
package io.github.carlomicieli.railways.usecases.validation;

import io.github.carlomicieli.railways.usecases.RailwayLengthInput;
import io.github.carlomicieli.railways.usecases.validation.constraints.RailwayLength;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RailwayLengthValidator
    implements ConstraintValidator<RailwayLength, RailwayLengthInput> {
  @Override
  public boolean isValid(
      RailwayLengthInput totalRailwayLengthInput,
      ConstraintValidatorContext constraintValidatorContext) {
    return true;
  }
}
