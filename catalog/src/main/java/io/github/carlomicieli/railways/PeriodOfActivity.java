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
package io.github.carlomicieli.railways;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.With;

@AllArgsConstructor
@Data
@Builder
@With
public final class PeriodOfActivity {
  private final LocalDate operatingSince;
  private final LocalDate operatingUntil;
  private final RailwayStatus railwayStatus;

  public static PeriodOfActivity activeRailway(LocalDate operatingSince) {
    throw new RuntimeException("TODO");
  }

  public static PeriodOfActivity inactiveRailway(
      LocalDate operatingSince, LocalDate operatingUntil) {
    throw new RuntimeException("TODO");
  }

  public static PeriodOfActivity defaultPeriodOfActivity() {
    throw new RuntimeException("TODO");
  }

  private static boolean validate(
      RailwayStatus status, LocalDate operatingSince, LocalDate operatingUntil) {
    throw new RuntimeException("TODO");
  }
}
