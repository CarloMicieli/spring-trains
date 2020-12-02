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
package io.github.carlomicieli.catalogitems.rollingstocks;

import io.github.carlomicieli.lengths.Length;
import io.github.carlomicieli.lengths.MeasureUnit;
import io.github.carlomicieli.lengths.TwoLengths;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public final class LengthOverBuffer implements Comparable<LengthOverBuffer> {
  private static TwoLengths TwoLengths =
      new TwoLengths(MeasureUnit.INCHES, MeasureUnit.MILLIMETERS);

  private final Length inches;
  private final Length millimeters;

  public static LengthOverBuffer of(BigDecimal inches, BigDecimal millimeters) {
    throw new UnsupportedOperationException();
  }

  public static LengthOverBuffer ofMillimeters(BigDecimal millimeters) {
    throw new UnsupportedOperationException();
  }

  public static LengthOverBuffer ofInches(BigDecimal inches) {
    throw new UnsupportedOperationException();
  }

  @Override
  public int compareTo(LengthOverBuffer o) {
    return 0;
  }
}
