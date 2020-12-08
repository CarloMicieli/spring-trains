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

import io.github.carlomicieli.lengths.Length;
import io.github.carlomicieli.lengths.MeasureUnit;
import java.math.BigDecimal;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class LengthConverter implements AttributeConverter<Length, BigDecimal> {
  private final MeasureUnit measureUnit;

  @Override
  public BigDecimal convertToDatabaseColumn(Length length) {
    if (length == null) {
      return null;
    }

    return length.getValue();
  }

  @Override
  public Length convertToEntityAttribute(BigDecimal value) {
    if (value == null) {
      return null;
    }
    return Length.of(value, measureUnit);
  }

  @Converter
  public static class MILLIMETERS extends LengthConverter {
    public MILLIMETERS() {
      super(MeasureUnit.MILLIMETERS);
    }
  }

  @Converter
  public static class INCHES extends LengthConverter {
    public INCHES() {
      super(MeasureUnit.INCHES);
    }
  }

  @Converter
  public static class KILOMETERS extends LengthConverter {
    public KILOMETERS() {
      super(MeasureUnit.KILOMETERS);
    }
  }

  @Converter
  public static class MILES extends LengthConverter {
    public MILES() {
      super(MeasureUnit.MILES);
    }
  }
}
