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
package io.github.carlomicieli.persistence.catalog.brands;

import io.github.carlomicieli.brands.Brand;
import io.github.carlomicieli.brands.BrandId;
import io.github.carlomicieli.brands.queries.BrandQueriesRepository;
import io.github.carlomicieli.queries.pagination.Page;
import io.github.carlomicieli.queries.pagination.PaginatedResult;
import io.github.carlomicieli.queries.sorting.Sorting;
import io.github.carlomicieli.util.Slug;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class JpaBrandQueriesRepository implements BrandQueriesRepository {
  private final JpaBrandRepository jpaRepo;
  private final Converter<JpaBrand, Brand> converter = new FromJpaToDomainBrandConverter();

  @Override
  public Optional<Brand> findBySlug(Slug slug) {
    return jpaRepo.findBySlug(slug).map(converter::convert);
  }

  @Override
  public PaginatedResult<Brand> findBrands(Page currentPage, Sorting orderBy) {
    var pageRequest = PageRequest.of(0, currentPage.getLimit());

    var page = jpaRepo.findAll(pageRequest);
    return PaginatedResult.of(
        currentPage, page.stream().map(converter::convert).collect(Collectors.toList()));
  }

  static class FromJpaToDomainBrandConverter implements Converter<JpaBrand, Brand> {
    @Override
    public Brand convert(@Nonnull JpaBrand jpaBrand) {
      return Brand.builder()
          .id(BrandId.of(jpaBrand.id))
          .name(jpaBrand.name)
          .slug(jpaBrand.slug)
          .brandKind(jpaBrand.brandKind)
          .mailAddress(jpaBrand.mailAddress)
          .description(jpaBrand.description)
          .groupName(jpaBrand.groupName)
          .websiteUrl(jpaBrand.websiteUrl)
          .version(jpaBrand.version)
          .createdDate(jpaBrand.createdDate)
          .modifiedDate(jpaBrand.modifiedDate)
          .build();
    }
  }
}
