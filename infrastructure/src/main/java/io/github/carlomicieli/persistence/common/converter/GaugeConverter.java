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
package io.github.carlomicieli.persistence.common.converter;

import io.github.carlomicieli.lengths.MeasureUnit;
import io.github.carlomicieli.catalog.valueobject.Gauge;
import java.math.BigDecimal;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class GaugeConverter implements AttributeConverter<Gauge, BigDecimal> {
  private final MeasureUnit measureUnit;

  @Override
  public BigDecimal convertToDatabaseColumn(Gauge gauge) {
    if (gauge == null) {
      return null;
    }
    return gauge.getValue();
  }

  @Override
  public Gauge convertToEntityAttribute(BigDecimal value) {
    if (value == null) {
      return null;
    }
    return Gauge.of(value, measureUnit);
  }

  @Converter
  public static class MILLIMETERS extends GaugeConverter {
    public MILLIMETERS() {
      super(MeasureUnit.MILLIMETERS);
    }
  }

  @Converter
  public static class INCHES extends GaugeConverter {
    public INCHES() {
      super(MeasureUnit.INCHES);
    }
  }
}
