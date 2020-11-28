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
package io.github.carlomicieli.web.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import io.github.carlomicieli.catalogItems.CatalogItem;
import io.github.carlomicieli.repository.CatalogItemsRepository;
import io.github.carlomicieli.web.representation.CatalogItemRepresentation;
import io.github.carlomicieli.web.request.NewCatalogItem;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/catalogItems")
@Log4j2
@AllArgsConstructor
public class CatalogItemsController {

  private final CatalogItemsRepository catalogItems;

  @GetMapping()
  public ResponseEntity<List<CatalogItem>> getAllItems() {
    log.debug("GET all catalog items");

    var items = catalogItems.findAll();
    return ResponseEntity.ok(items);
  }

  @GetMapping("/{id}")
  public HttpEntity<CatalogItemRepresentation> getById(@PathVariable Integer id) {
    log.debug("GET catalog item with id = {}", id);

    return catalogItems
        .findOneById(id)
        .map(
            item -> {
              var catalogItem = new CatalogItemRepresentation(item);
              catalogItem.add(
                  linkTo(methodOn(CatalogItemsController.class).getById(item.getId()))
                      .withSelfRel());
              return catalogItem;
            })
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @SneakyThrows
  @PostMapping()
  public ResponseEntity<Void> postCatalogItem(@RequestBody NewCatalogItem newItem) {
    log.debug("POST {}", newItem);

    var item = new CatalogItem();
    item.setBrand(newItem.getBrand());
    item.setItemNumber(newItem.getItemNumber());
    item.setDescription(newItem.getDescription());
    item.setCategory(newItem.getCategory());

    var saved = catalogItems.saveAndFlush(item);
    var link = linkTo(methodOn(CatalogItemsController.class).getById(saved.getId())).withSelfRel();
    return ResponseEntity.created(link.toUri()).build();
  }
}
