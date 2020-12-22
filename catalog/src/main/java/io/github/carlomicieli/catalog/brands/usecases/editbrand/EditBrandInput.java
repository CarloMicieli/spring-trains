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
package io.github.carlomicieli.catalog.brands.usecases.editbrand;

import io.github.carlomicieli.catalog.brands.usecases.createbrand.CreateBrandAddressInput;
import io.github.carlomicieli.usecases.boundaries.input.UseCaseInput;
import io.github.carlomicieli.util.Slug;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
@Builder
public class EditBrandInput implements UseCaseInput {

  Slug slug;
  @Valid ModifiedValues values;

  @Value
  @Builder
  public static class ModifiedValues {
    @NotBlank()
    @Length(max = 50)
    String name;

    @Length(max = 250)
    String companyName;

    @Length(max = 250)
    String groupName;

    @Length(max = 250)
    String description;

    @Length(max = 100)
    String websiteUrl;

    @Length(max = 100)
    String mailAddress;

    @NotBlank() String brandKind;

    CreateBrandAddressInput address;
  }
}
