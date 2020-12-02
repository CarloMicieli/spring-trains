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
package io.github.carlomicieli.web.catalog.catalogItems.representation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.carlomicieli.catalogitems.CatalogItem;
import io.github.carlomicieli.web.catalog.catalogItems.getById.CatalogItemsController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

public class CatalogItemRepresentation extends RepresentationModel<CatalogItemRepresentation> {
  private final CatalogItem data;

  @JsonCreator
  public CatalogItemRepresentation(@JsonProperty("data") CatalogItem data) {
    this.data = data;
  }

  public CatalogItem getCatalogItem() {
    return data;
  }

  public static CatalogItemRepresentation of(CatalogItem item) {
    var catalogItem = new CatalogItemRepresentation(item);
    catalogItem.add(
        linkTo(methodOn(CatalogItemsController.class).getById(item.getId())).withSelfRel());
    return catalogItem;
  }

  public static Link selfLink(CatalogItem item) {
    return linkTo(methodOn(CatalogItemsController.class).getById(item.getId())).withSelfRel();
  }
}
