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
import io.github.carlomicieli.railways.RailwayLength;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class JpaRailwayLengthTest {
  private static final Length MILES_LENGTH = Length.ofMiles(BigDecimal.valueOf(100));
  private static final Length KILOMETERS_LENGTH = Length.ofMiles(BigDecimal.valueOf(50));

  @Test
  void should_convert_from_domain_object() {
    var railwayLength =
        RailwayLength.builder().miles(MILES_LENGTH).kilometers(KILOMETERS_LENGTH).build();

    var jpaRailwayLength = JpaRailwayLength.fromDomain(railwayLength);

    assertThat(jpaRailwayLength).isNotNull();
    assertThat(jpaRailwayLength.getKilometers()).isEqualTo(railwayLength.getKilometers());
    assertThat(jpaRailwayLength.getMiles()).isEqualTo(railwayLength.getMiles());
  }

  @Test
  void should_convert_to_domain_objects() {
    var jpaRailwayLength =
        JpaRailwayLength.builder().miles(MILES_LENGTH).kilometers(KILOMETERS_LENGTH).build();

    var railwayLength = jpaRailwayLength.toDomain();

    assertThat(railwayLength).isNotNull();
    assertThat(railwayLength.getKilometers()).isEqualTo(jpaRailwayLength.getKilometers());
    assertThat(railwayLength.getMiles()).isEqualTo(jpaRailwayLength.getMiles());
  }
}
