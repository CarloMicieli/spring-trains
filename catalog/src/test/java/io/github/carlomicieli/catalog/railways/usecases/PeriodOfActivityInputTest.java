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
package io.github.carlomicieli.catalog.railways.usecases;

import static org.assertj.core.api.Assertions.*;

import io.github.carlomicieli.catalog.railways.usecases.validation.constraints.PeriodOfActivity;
import java.time.LocalDate;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.*;

@DisplayName("PeriodOfActivityInput")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PeriodOfActivityInputTest {

  private final Validator validator;

  public PeriodOfActivityInputTest() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  void should_validate_valid_values() {
    var periodOfActivity =
        PeriodOfActivityInput.builder()
            .operatingSince(LocalDate.of(1988, 11, 25))
            .status("ACTIVE")
            .build();
    var testClass = TestClass.of(periodOfActivity);

    var errors = validator.validate(testClass);

    assertThat(errors).isEmpty();
  }

  @Test
  @Disabled
  void should_fail_to_validate_invalid_values() {
    var periodOfActivity = PeriodOfActivityInput.builder().build();
    var testClass = TestClass.of(periodOfActivity);

    var errors = validator.validate(testClass);

    assertThat(errors).isNotEmpty();
  }

  @AllArgsConstructor(staticName = "of")
  static class TestClass {
    @PeriodOfActivity PeriodOfActivityInput periodOfActivity;
  }
}
