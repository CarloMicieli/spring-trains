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
package io.github.carlomicieli.railways.usecases.editrailway;

import io.github.carlomicieli.railways.usecases.PeriodOfActivityInput;
import io.github.carlomicieli.railways.usecases.RailwayGaugeInput;
import io.github.carlomicieli.railways.usecases.RailwayLengthInput;
import io.github.carlomicieli.railways.usecases.validation.constraints.PeriodOfActivity;
import io.github.carlomicieli.railways.usecases.validation.constraints.RailwayGauge;
import io.github.carlomicieli.railways.usecases.validation.constraints.RailwayLength;
import io.github.carlomicieli.usecases.boundaries.input.UseCaseInput;
import io.github.carlomicieli.util.Slug;
import io.github.carlomicieli.validation.annotation.constraints.ISOCountry;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.*;

@Value
@AllArgsConstructor(staticName = "of")
public class EditRailwayInput implements UseCaseInput {
  Slug slug;

  @Valid ModifiedValues values;

  @Value
  @Builder
  public static class ModifiedValues {
    @NotBlank
    @Size(max = 25)
    String name;

    @NotBlank
    @Size(max = 250)
    String companyName;

    @ISOCountry
    @Size(max = 2)
    String country;

    @PeriodOfActivity PeriodOfActivityInput periodOfActivity;

    @RailwayLength RailwayLengthInput totalLength;

    @RailwayGauge RailwayGaugeInput gauge;

    @Size(max = 255)
    String websiteUrl;

    @Size(max = 250)
    String headquarters;
  }
}
