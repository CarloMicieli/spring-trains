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
package io.github.carlomicieli.railways;

import static org.junit.jupiter.api.Assertions.*;

import io.github.carlomicieli.lengths.Length;
import io.github.carlomicieli.lengths.MeasureUnit;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayName("A railway length")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RailwayLengthTest {

  @Test
  void is_created_via_a_builder() {
    var oneHundred = BigDecimal.valueOf(100);
    var railwayLength =
        RailwayLength.builder().kilometers(new Length(oneHundred, MeasureUnit.KILOMETERS)).build();

    assertEquals(oneHundred, railwayLength.getKilometers().getValue());
  }
}
