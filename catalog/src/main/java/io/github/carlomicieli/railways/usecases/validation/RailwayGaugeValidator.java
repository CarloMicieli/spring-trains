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

import com.google.common.base.Strings;
import io.github.carlomicieli.railways.usecases.RailwayGaugeInput;
import io.github.carlomicieli.railways.usecases.validation.constraints.RailwayGauge;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RailwayGaugeValidator implements ConstraintValidator<RailwayGauge, RailwayGaugeInput> {
  @Override
  public void initialize(RailwayGauge constraintAnnotation) {}

  @Override
  public boolean isValid(
      RailwayGaugeInput railwayGaugeInput, ConstraintValidatorContext constraintValidatorContext) {
    if (Strings.isNullOrEmpty(railwayGaugeInput.getTrackGauge())) {
      return false;
    }

    if (railwayGaugeInput.getInches() != null && railwayGaugeInput.getInches().signum() < 0) {
      return false;
    }

    if (railwayGaugeInput.getMillimeters() != null
        && railwayGaugeInput.getMillimeters().signum() < 0) {
      return false;
    }

    return true;
  }
}
