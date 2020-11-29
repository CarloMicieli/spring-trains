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
package io.github.carlomicieli.valueObjects;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

// Gauge is the distance between the inner edges of the two rails that it runs on.
@AllArgsConstructor
@Data
public final class Gauge implements Comparable<Gauge> {

  private final BigDecimal value;
  private final MeasureUnit measureUnit;

  public static Gauge of(float value, MeasureUnit measureUnit) {
    throw new RuntimeException("TODO");
  }

  public static Gauge ofMillimeters(BigDecimal value) {
    return new Gauge(value, MeasureUnit.MILLIMETERS);
  }

  public static Gauge ofInches(BigDecimal value) {
    return new Gauge(value, MeasureUnit.MILLIMETERS);
  }

  @Override
  public int compareTo(Gauge other) {
    return 0;
  }
}
