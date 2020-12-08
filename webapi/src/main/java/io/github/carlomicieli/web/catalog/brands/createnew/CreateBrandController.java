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
package io.github.carlomicieli.web.catalog.brands.createnew;

import io.github.carlomicieli.brands.usecases.createbrand.CreateBrandInput;
import io.github.carlomicieli.brands.usecases.createbrand.CreateBrandUseCase;
import javax.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Log4j2
public class CreateBrandController {
  private final CreateBrandUseCase useCase;
  private final CreateBrandPresenter presenter;

  @PostMapping("api/brands")
  public ResponseEntity<?> postNewBrand(@RequestBody @Nonnull NewBrandRequest request) {
    var input =
        CreateBrandInput.builder()
            .name(request.getName())
            .brandKind(request.getBrandKind())
            .build();

    useCase.execute(input);
    return presenter.getResponse();
  }
}
