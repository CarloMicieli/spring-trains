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

import io.github.carlomicieli.railways.PeriodOfActivity;
import io.github.carlomicieli.railways.RailwayStatus;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayName("A jpa period of activity")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class JpaPeriodOfActivityTest {

  private final LocalDate OPERATING_SINCE = LocalDate.of(1905, 7, 1);
  private final LocalDate OPERATING_UNTIL = LocalDate.of(2000, 1, 1);

  @Test
  void is_should_convert_a_value_from_domain_object() {
    var periodOfActivity =
        new PeriodOfActivity(RailwayStatus.INACTIVE, OPERATING_SINCE, OPERATING_UNTIL);
    var jpaPeriodOfActivity = JpaPeriodOfActivity.fromDomain(periodOfActivity);

    assertThat(jpaPeriodOfActivity).isNotNull();
    assertThat(jpaPeriodOfActivity.isActive()).isFalse();
    assertThat(jpaPeriodOfActivity.getOperatingSince()).isEqualTo(OPERATING_SINCE);
    assertThat(jpaPeriodOfActivity.getOperatingUntil()).isEqualTo(OPERATING_UNTIL);
  }

  @Test
  void is_should_convert_to_domain_objects() {
    var jpaPeriodOfActivity =
        JpaPeriodOfActivity.builder()
            .active(false)
            .operatingSince(OPERATING_SINCE)
            .operatingUntil(OPERATING_UNTIL)
            .build();
    var periodOfActivity = jpaPeriodOfActivity.toDomain();

    assertThat(periodOfActivity).isNotNull();
    assertThat(periodOfActivity.isActive()).isFalse();
    assertThat(periodOfActivity.getOperatingSince()).isEqualTo(OPERATING_SINCE);
    assertThat(periodOfActivity.getOperatingUntil()).isEqualTo(OPERATING_UNTIL);
  }
}
