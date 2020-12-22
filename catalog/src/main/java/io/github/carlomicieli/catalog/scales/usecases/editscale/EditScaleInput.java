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
package io.github.carlomicieli.catalog.scales.usecases.editscale;

import io.github.carlomicieli.catalog.scales.usecases.ScaleGaugeInput;
import io.github.carlomicieli.usecases.boundaries.input.UseCaseInput;
import io.github.carlomicieli.util.Slug;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EditScaleInput implements UseCaseInput {

  Slug slug;

  @Valid ModifiedValues values;

  @Value
  @Builder
  public static class ModifiedValues {
    @NotBlank
    @Size(max = 25)
    String name;

    BigDecimal ratio;

    ScaleGaugeInput gauge;

    String description;

    List<String> standards;

    Integer weight;
  }
}
