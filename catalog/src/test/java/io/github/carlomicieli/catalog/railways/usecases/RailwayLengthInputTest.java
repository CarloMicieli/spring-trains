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

import io.github.carlomicieli.catalog.railways.usecases.validation.constraints.RailwayLength;
import java.math.BigDecimal;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayName("RailwayLengthInput")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RailwayLengthInputTest {
  private final Validator validator;

  public RailwayLengthInputTest() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  void should_validate_valid_values() {
    var railwayLength = RailwayLengthInput.builder().kilometers(BigDecimal.TEN).build();
    var testClass = TestClass.of(railwayLength);

    var errors = validator.validate(testClass);

    assertThat(errors).isEmpty();
  }

  @AllArgsConstructor(staticName = "of")
  static class TestClass {
    @RailwayLength RailwayLengthInput railwayLength;
  }
}
