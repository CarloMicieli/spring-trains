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
package io.github.carlomicieli.web.catalog.brands.getbrandbyslug;

import io.github.carlomicieli.brands.Brand;
import io.github.carlomicieli.brands.queries.getbrandbyslug.GetBrandBySlugCriteria;
import io.github.carlomicieli.brands.queries.getbrandbyslug.GetBrandBySlugQuery;
import io.github.carlomicieli.util.Slug;
import io.github.carlomicieli.web.catalog.brands.BrandRepresentation;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Log4j2
@ExposesResourceFor(Brand.class)
public class GetBrandBySlugController {

  private final EntityLinks entityLinks;
  private final GetBrandBySlugQuery query;

  @GetMapping("/api/brands/{slug}")
  public ResponseEntity<EntityModel<Brand>> getBrandBySlug(@PathVariable String slug) {
    var criteria = new GetBrandBySlugCriteria(Slug.of(slug));
    return query
        .execute(criteria)
        .map(brand -> BrandRepresentation.of(brand, getLinks(slug)))
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  private List<Link> getLinks(String slug) {
    return Collections.singletonList(entityLinks.linkToItemResource(Brand.class, slug));
  }
}
