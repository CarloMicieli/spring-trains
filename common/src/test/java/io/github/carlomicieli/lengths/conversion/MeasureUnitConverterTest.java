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
package io.github.carlomicieli.lengths.conversion;

import static org.junit.jupiter.api.Assertions.*;

import io.github.carlomicieli.lengths.MeasureUnit;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayName("Measure unit conversion")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MeasureUnitConverterTest {

  @Test
  void it_should_throw_an_exception_when_conversion_is_not_valid() {
    var converter = new InvalidConverter(MeasureUnit.INCHES, MeasureUnit.KILOMETERS);
    var ex = assertThrows(RuntimeException.class, () -> converter.convert(BigDecimal.TEN));
    assertEquals("Unable to find a suitable converter from INCHES to KILOMETERS", ex.getMessage());
  }

  @Test
  void it_should_return_back_the_original_value_for_same_measure_units_converter() {
    var converter = new SameUnitConverter();
    var expected = BigDecimal.TEN;
    var actual = converter.convert(expected);

    assertEquals(expected, actual);
  }

  @Test
  void it_should_convert_between_measure_units_using_a_conversion_rate() {
    var converter =
        new MeasureUnitConverterByRate(
            MeasureUnit.INCHES, MeasureUnit.MILLIMETERS, ConversionRate.INCHES_TO_MILLIMETERS);
    var result = converter.convert(BigDecimal.ONE, 5);
    assertEquals(BigDecimal.valueOf(25.4), result);
  }
}
