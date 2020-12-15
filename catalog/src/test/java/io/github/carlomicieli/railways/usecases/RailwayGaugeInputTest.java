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
package io.github.carlomicieli.railways.usecases;

import static org.assertj.core.api.Assertions.*;

import io.github.carlomicieli.railways.usecases.validation.constraints.RailwayGauge;
import java.math.BigDecimal;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayName("RailwayGaugeInput")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RailwayGaugeInputTest {

  private final Validator validator;

  public RailwayGaugeInputTest() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  void should_validate_correct_inputs() {
    var input =
        RailwayGaugeInput.builder()
            .trackGauge("NARROW")
            .inches(BigDecimal.valueOf(0.65))
            .millimeters(BigDecimal.valueOf(16.5))
            .build();
    var testClass = TestClass.of(input);

    var errors = validator.validate(testClass);
    assertThat(errors).isEmpty();
  }

  @Test
  void should_fail_to_validate_invalid_inputs() {
    var input = RailwayGaugeInput.builder().build();
    var testClass = TestClass.of(input);

    var errors = validator.validate(testClass);
    assertThat(errors).isNotEmpty();
  }

  @AllArgsConstructor(staticName = "of")
  static class TestClass {
    @RailwayGauge RailwayGaugeInput railwayGauge;
  }
}
