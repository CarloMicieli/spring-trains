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
package io.github.carlomicieli.web.catalog.brands;

import io.github.carlomicieli.brands.Brand;
import io.github.carlomicieli.brands.BrandFactory;
import io.github.carlomicieli.brands.BrandId;
import io.github.carlomicieli.brands.BrandKind;
import io.github.carlomicieli.brands.queries.BrandQueriesRepository;
import io.github.carlomicieli.brands.queries.getbrandbyslug.GetBrandBySlugQuery;
import io.github.carlomicieli.brands.usecases.BrandUseCaseRepository;
import io.github.carlomicieli.brands.usecases.createbrand.CreateBrandInput;
import io.github.carlomicieli.brands.usecases.createbrand.CreateBrandOutputPort;
import io.github.carlomicieli.brands.usecases.createbrand.CreateBrandUseCase;
import io.github.carlomicieli.queries.pagination.Page;
import io.github.carlomicieli.queries.pagination.PaginatedResult;
import io.github.carlomicieli.queries.sorting.Sorting;
import io.github.carlomicieli.usecases.boundaries.input.UseCaseInputValidator;
import io.github.carlomicieli.util.Slug;
import java.time.Clock;
import java.util.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrandConfiguration {

  @Bean
  public GetBrandBySlugQuery getBrandBySlugQuery(BrandQueriesRepository brandQueriesRepository) {
    return new GetBrandBySlugQuery(brandQueriesRepository);
  }

  @Bean
  public BrandQueriesRepository brandQueriesRepository() {
    return new BrandQueriesRepository() {
      final List<Brand> brands =
          Collections.singletonList(
              Brand.builder()
                  .name("ACME")
                  .slug(Slug.of("ACME"))
                  .brandKind(BrandKind.INDUSTRIAL)
                  .build());

      @Override
      public Optional<Brand> findBySlug(Slug slug) {
        return brands.stream().filter(b -> slug.equals(b.getSlug())).findFirst();
      }

      @Override
      public PaginatedResult<Brand> findBrands(Page currentPage, Sorting orderBy) {
        return null;
      }
    };
  }

  @Bean
  public BrandUseCaseRepository brandUseCaseRepository() {
    return new BrandUseCaseRepository() {
      final List<Brand> brands = new ArrayList<>();

      @Override
      public boolean exists(Slug brandSlug) {
        return brands.stream().anyMatch(b -> brandSlug.equals(b.getSlug()));
      }

      @Override
      public void save(Brand brand) {
        brands.add(brand);
      }
    };
  }

  @Bean
  public BrandFactory brandFactory(Clock clock) {
    return new BrandFactory(clock, BrandId::randomId);
  }

  @Bean
  public CreateBrandUseCase createBrandUseCase(
      UseCaseInputValidator<CreateBrandInput> inputValidator,
      CreateBrandOutputPort outputPort,
      BrandFactory brandFactory,
      BrandUseCaseRepository repo) {
    return new CreateBrandUseCase(inputValidator, outputPort, brandFactory, repo);
  }
}
