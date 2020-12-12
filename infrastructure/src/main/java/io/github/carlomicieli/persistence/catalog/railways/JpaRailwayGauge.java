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
import io.github.carlomicieli.railways.RailwayGauge;
import io.github.carlomicieli.valueobject.TrackGauge;

import javax.persistence.*;

import lombok.*;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class JpaRailwayGauge {
  @Column(name = "track_type")
  @Enumerated(EnumType.STRING)
  TrackGauge trackGauge;

  @Convert(converter = LengthConverter.MILLIMETERS.class)
  @Column(name = "gauge_mm")
  Length millimeters;

  @Convert(converter = LengthConverter.INCHES.class)
  @Column(name = "gauge_in")
  Length inches;

  public RailwayGauge toDomain() {
    return new RailwayGauge(trackGauge, millimeters, inches);
  }

  public static JpaRailwayGauge fromDomain(RailwayGauge rg) {
    return new JpaRailwayGauge(rg.getTrackGauge(), rg.getMillimeters(), rg.getInches());
  }
}
