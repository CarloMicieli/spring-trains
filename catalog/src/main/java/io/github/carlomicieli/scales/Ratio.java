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
package io.github.carlomicieli.scales;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

// It represents the <em>Ratio</em> between a model railway size
// and the size of an actual train.
@Data
@AllArgsConstructor
public final class Ratio implements Comparable<Ratio> {
  private final BigDecimal value;

  @Override
  public int compareTo(Ratio o) {
    return 0;
  }
}
