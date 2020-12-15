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
package io.github.carlomicieli.railways.usecases.createrailway;

import io.github.carlomicieli.railways.usecases.PeriodOfActivityInput;
import io.github.carlomicieli.railways.usecases.RailwayGaugeInput;
import io.github.carlomicieli.railways.usecases.RailwayLengthInput;
import io.github.carlomicieli.railways.usecases.validation.constraints.PeriodOfActivity;
import io.github.carlomicieli.railways.usecases.validation.constraints.RailwayGauge;
import io.github.carlomicieli.railways.usecases.validation.constraints.RailwayLength;
import io.github.carlomicieli.usecases.boundaries.input.UseCaseInput;
import io.github.carlomicieli.validation.annotation.constraints.ISOCountry;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@Builder
@With
public class CreateRailwayInput implements UseCaseInput {
  @NotBlank
  @Size(max = 25)
  String name;

  @Size(max = 250)
  String companyName;

  @Size(max = 2)
  @ISOCountry
  String country;

  @PeriodOfActivity PeriodOfActivityInput periodOfActivity;

  @RailwayLength RailwayLengthInput totalLength;

  @RailwayGauge RailwayGaugeInput railwayGauge;

  @Size(max = 255)
  String websiteUrl;

  @Size(max = 250)
  String headquarters;
}
