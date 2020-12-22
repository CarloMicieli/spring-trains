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

import io.github.carlomicieli.catalog.catalogitems.rollingstocks.MinRadius;
import java.math.BigDecimal;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class MinRadiusConverter implements AttributeConverter<MinRadius, BigDecimal> {
  @Override
  public BigDecimal convertToDatabaseColumn(MinRadius minRadius) {
    if (minRadius == null) {
      return null;
    }

    return minRadius.getMillimeters();
  }

  @Override
  public MinRadius convertToEntityAttribute(BigDecimal value) {
    if (value == null) {
      return null;
    }

    return MinRadius.ofMillimeters(value);
  }
}
