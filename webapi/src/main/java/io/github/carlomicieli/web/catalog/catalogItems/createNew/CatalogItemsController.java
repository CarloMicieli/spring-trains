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
package io.github.carlomicieli.web.catalog.catalogItems.createNew;

import io.github.carlomicieli.repository.CatalogItemsRepository;
import io.github.carlomicieli.web.catalog.catalogItems.representation.CatalogItemRepresentation;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/catalogItems")
@Log4j2
@AllArgsConstructor
public class CatalogItemsController {

  private final CatalogItemsRepository catalogItems;

  @SneakyThrows
  @PostMapping()
  public ResponseEntity<Void> postCatalogItem(@RequestBody @Valid NewCatalogItem newItem) {
    log.debug("POST {}", newItem);

    // var saved = catalogItems.saveAndFlush(item);
    return ResponseEntity.created(CatalogItemRepresentation.selfLink(null).toUri()).build();
  }
}
