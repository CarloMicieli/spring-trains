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

import static org.assertj.core.api.Assertions.assertThat;

import io.github.carlomicieli.lengths.Length;
import io.github.carlomicieli.railways.RailwayGauge;
import io.github.carlomicieli.valueobject.TrackGauge;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayName("A jpa railway gauge")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class JpaRailwayGaugeTest {

  private static final Length LENGTH_INCHES = Length.ofInches(BigDecimal.valueOf(0.65));
  private static final Length LENGTH_MILLIMETERS = Length.ofInches(BigDecimal.valueOf(16.5));

  @Test
  void is_should_convert_from_domain_object() {
    var railwayGauge =
        RailwayGauge.builder()
            .trackGauge(TrackGauge.STANDARD)
            .inches(LENGTH_INCHES)
            .millimeters(LENGTH_MILLIMETERS)
            .build();
    var jpaRailwayGauge = JpaRailwayGauge.fromDomain(railwayGauge);

    assertThat(jpaRailwayGauge).isNotNull();
    assertThat(jpaRailwayGauge.getTrackGauge()).isEqualTo(railwayGauge.getTrackGauge());
    assertThat(jpaRailwayGauge.getInches()).isEqualTo(railwayGauge.getInches());
    assertThat(jpaRailwayGauge.getMillimeters()).isEqualTo(railwayGauge.getMillimeters());
  }

  @Test
  void is_should_convert_to_domain_objects() {
    var jpaRailwayGauge =
        JpaRailwayGauge.builder()
            .trackGauge(TrackGauge.STANDARD)
            .inches(LENGTH_INCHES)
            .millimeters(LENGTH_MILLIMETERS)
            .build();
    var railwayGauge = jpaRailwayGauge.toDomain();

    assertThat(railwayGauge).isNotNull();
    assertThat(railwayGauge.getTrackGauge()).isEqualTo(jpaRailwayGauge.getTrackGauge());
    assertThat(railwayGauge.getInches()).isEqualTo(jpaRailwayGauge.getInches());
    assertThat(railwayGauge.getMillimeters()).isEqualTo(jpaRailwayGauge.getMillimeters());
  }
}
