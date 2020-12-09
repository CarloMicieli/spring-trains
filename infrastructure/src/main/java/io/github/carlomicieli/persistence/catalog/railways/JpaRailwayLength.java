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
package io.github.carlomicieli.persistence.catalog.railways;

import io.github.carlomicieli.lengths.Length;
import io.github.carlomicieli.persistence.common.converter.LengthConverter;
import io.github.carlomicieli.railways.RailwayLength;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class JpaRailwayLength {
  @Convert(converter = LengthConverter.KILOMETERS.class)
  @Column(name = "total_length_km")
  Length kilometers;

  @Convert(converter = LengthConverter.MILES.class)
  @Column(name = "total_length_mi")
  Length miles;

  public static JpaRailwayLength fromDomain(RailwayLength rl) {
    return new JpaRailwayLength(rl.getKilometers(), rl.getMiles());
  }
}
