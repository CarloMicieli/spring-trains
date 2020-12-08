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

import io.github.carlomicieli.brands.usecases.createbrand.CreateBrandOutput;
import io.github.carlomicieli.brands.usecases.createbrand.CreateBrandOutputPort;
import io.github.carlomicieli.web.presenters.ServerResponseOutputPort;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@Log4j2
public class CreateBrandPresenter extends ServerResponseOutputPort<CreateBrandOutput>
    implements CreateBrandOutputPort {

  @Override
  public void brandAlreadyExists(String nameAlreadyUsed) {
    log.warn("Brand {} already exists", nameAlreadyUsed);
    setResponse(ResponseEntity.status(HttpStatus.CONFLICT).body("Brand already exists"));
  }

  @Override
  public void standard(CreateBrandOutput output) {
    var slug = output.getSlug();

    setResponse(ResponseEntity.ok().body(String.format("%d", hashCode())));
  }
}
