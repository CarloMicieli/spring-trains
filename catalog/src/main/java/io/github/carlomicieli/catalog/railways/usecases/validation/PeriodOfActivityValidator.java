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
package io.github.carlomicieli.catalog.railways.usecases.validation;

import io.github.carlomicieli.catalog.railways.usecases.PeriodOfActivityInput;
import io.github.carlomicieli.catalog.railways.usecases.validation.constraints.PeriodOfActivity;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PeriodOfActivityValidator
    implements ConstraintValidator<PeriodOfActivity, PeriodOfActivityInput> {
  @Override
  public boolean isValid(
      PeriodOfActivityInput periodOfActivityInput,
      ConstraintValidatorContext constraintValidatorContext) {
    return true;
  }
}
