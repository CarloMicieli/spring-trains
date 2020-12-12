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
package io.github.carlomicieli.persistence.catalog.scales;

import io.github.carlomicieli.persistence.common.converter.GaugeConverter;
import io.github.carlomicieli.scales.ScaleGauge;
import io.github.carlomicieli.valueobject.Gauge;
import io.github.carlomicieli.valueobject.TrackGauge;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class JpaScaleGauge {
  @Column(name = "track_type")
  @Enumerated(EnumType.STRING)
  TrackGauge trackGauge;

  @Column(name = "gauge_mm")
  @Convert(converter = GaugeConverter.MILLIMETERS.class)
  Gauge millimetres;

  @Column(name = "gauge_in")
  @Convert(converter = GaugeConverter.INCHES.class)
  Gauge inches;

  public ScaleGauge toDomain() {
    return new ScaleGauge(millimetres, inches, trackGauge);
  }

  public static JpaScaleGauge fromDomain(ScaleGauge g) {
    return new JpaScaleGauge(g.getTrackGauge(), g.getMillimetres(), g.getInches());
  }
}
