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

import io.github.carlomicieli.lengths.Length;
import io.github.carlomicieli.lengths.MeasureUnit;
import io.github.carlomicieli.lengths.conversion.MeasureUnitsConverters;
import io.github.carlomicieli.valueobject.TrackGauge;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.With;

@Data
@With
@AllArgsConstructor
@Builder
public final class RailwayGauge {
  private final TrackGauge trackGauge;
  private final Length millimeters;
  private final Length inches;

  /** Creates a new {@code RailwayGauge} value in millimeters, using the provided track gauge */
  public static RailwayGauge ofMillimeters(double value, TrackGauge trackGauge) {
    var lengthValue = BigDecimal.valueOf(value);
    var converter =
        MeasureUnitsConverters.INSTANCE.getConverter(MeasureUnit.MILLIMETERS, MeasureUnit.INCHES);
    return new RailwayGauge(
        trackGauge,
        Length.of(lengthValue, MeasureUnit.MILLIMETERS),
        Length.of(converter.convert(lengthValue, 2), MeasureUnit.INCHES));
  }

  /** Creates a new {@code RailwayGauge} value in inches, using the provided track gauge */
  public static RailwayGauge ofInches(double value, TrackGauge trackGauge) {
    var lengthValue = BigDecimal.valueOf(value);
    var converter =
        MeasureUnitsConverters.INSTANCE.getConverter(MeasureUnit.INCHES, MeasureUnit.MILLIMETERS);
    return new RailwayGauge(
        trackGauge,
        Length.of(converter.convert(lengthValue, 2), MeasureUnit.MILLIMETERS),
        Length.of(lengthValue, MeasureUnit.INCHES));
  }
}
