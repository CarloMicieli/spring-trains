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

import static org.assertj.core.api.Assertions.assertThat;

import io.github.carlomicieli.scales.ScaleGauge;
import io.github.carlomicieli.valueobject.Gauge;
import io.github.carlomicieli.valueobject.TrackGauge;
import org.junit.jupiter.api.Test;

class JpaScaleGaugeTest {
  private static final Gauge INCHES_GAUGE = Gauge.ofInches(0.65);
  private static final Gauge MILLIMETERS_GAUGE = Gauge.ofInches(16.5);

  @Test
  void should_convert_from_domain_object() {
    var scaleGauge =
        ScaleGauge.builder()
            .trackGauge(TrackGauge.STANDARD)
            .inches(INCHES_GAUGE)
            .millimetres(MILLIMETERS_GAUGE)
            .build();
    var jpaScaleGauge = JpaScaleGauge.fromDomain(scaleGauge);

    assertThat(jpaScaleGauge).isNotNull();

    assertThat(jpaScaleGauge.inches).isEqualTo(INCHES_GAUGE);
    assertThat(jpaScaleGauge.millimetres).isEqualTo(MILLIMETERS_GAUGE);
    assertThat(jpaScaleGauge.trackGauge).isEqualTo(scaleGauge.getTrackGauge());
  }

  @Test
  void should_convert_to_domain_objects() {
    var jpaScaleGauge =
        JpaScaleGauge.builder()
            .trackGauge(TrackGauge.STANDARD)
            .inches(INCHES_GAUGE)
            .millimetres(MILLIMETERS_GAUGE)
            .build();
    var scaleGauge = jpaScaleGauge.toDomain();

    assertThat(scaleGauge).isNotNull();

    assertThat(scaleGauge.getInches()).isEqualTo(INCHES_GAUGE);
    assertThat(scaleGauge.getMillimetres()).isEqualTo(MILLIMETERS_GAUGE);
    assertThat(scaleGauge.getTrackGauge()).isEqualTo(jpaScaleGauge.getTrackGauge());
  }
}
