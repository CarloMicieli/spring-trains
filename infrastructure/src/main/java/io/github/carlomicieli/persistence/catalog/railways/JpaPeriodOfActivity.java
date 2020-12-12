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

import io.github.carlomicieli.railways.PeriodOfActivity;
import io.github.carlomicieli.railways.RailwayStatus;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class JpaPeriodOfActivity {
  boolean active;

  @Column(name = "operating_since", columnDefinition = "TIMESTAMP")
  LocalDate operatingSince;

  @Column(name = "operating_until", columnDefinition = "TIMESTAMP")
  LocalDate operatingUntil;

  public static JpaPeriodOfActivity fromDomain(PeriodOfActivity poa) {
    return new JpaPeriodOfActivity(
        poa.isActive(), poa.getOperatingSince(), poa.getOperatingUntil());
  }

  public PeriodOfActivity toDomain() {
    return new PeriodOfActivity(
        active ? RailwayStatus.ACTIVE : RailwayStatus.INACTIVE, operatingSince, operatingUntil);
  }
}
