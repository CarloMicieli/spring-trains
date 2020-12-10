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

import static org.assertj.core.api.Assertions.*;

import io.github.carlomicieli.catalogitems.Control;
import io.github.carlomicieli.catalogitems.DccInterface;
import io.github.carlomicieli.catalogitems.Epoch;
import io.github.carlomicieli.railways.RailwayId;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayName("A RollingStock factory")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RollingStockFactoryTest {

  private static final RailwayId FIXED_RAILWAY_ID =
      RailwayId.of(UUID.fromString("e45f901f-0751-489c-910e-ce112d06ccfe"));
  private static final RollingStockId FIXED_ROLLING_STOCK_ID =
      RollingStockId.of(UUID.fromString("e9f5ed5a-edb6-46fa-a55a-bc8632e89d3a"));

  private final RollingStockFactory factory;

  public RollingStockFactoryTest() {
    this.factory = new RollingStockFactory(() -> FIXED_ROLLING_STOCK_ID);
  }

  @Test
  void should_create_new_locomotives() {
    var length = LengthOverBuffer.ofMillimeters(BigDecimal.valueOf(210));
    var locomotive =
        factory.createNewLocomotive(
            FIXED_RAILWAY_ID,
            Category.ELECTRIC_LOCOMOTIVE,
            Epoch.IV,
            length,
            MinRadius.ofMillimeters(360),
            Prototype.ofLocomotive("E656", "E656 210"),
            Couplers.NEM_352,
            "blu / grigio",
            "Milano Centrale",
            DccInterface.NEM_652,
            Control.DCC_READY);

    assertThat(locomotive).isNotNull();

    assertThat(locomotive.getId()).isEqualTo(FIXED_ROLLING_STOCK_ID);
    assertThat(locomotive.getRailway()).isEqualTo(FIXED_RAILWAY_ID);

    assertThat(locomotive.getCategory()).isEqualTo(Category.ELECTRIC_LOCOMOTIVE);
    assertThat(locomotive.getEpoch()).isEqualTo(Epoch.IV);
    assertThat(locomotive.getLengthOverBuffer()).isEqualTo(length);
    assertThat(locomotive.getMinRadius()).isEqualTo(MinRadius.ofMillimeters(360));
    assertThat(locomotive.getCouplers()).isEqualTo(Couplers.NEM_352);
    assertThat(locomotive.getLivery()).isEqualTo("blu / grigio");
    assertThat(locomotive.getDepot()).isEqualTo("Milano Centrale");
    assertThat(locomotive.getDccInterface()).isEqualTo(DccInterface.NEM_652);
    assertThat(locomotive.getControl()).isEqualTo(Control.DCC_READY);
  }
}
