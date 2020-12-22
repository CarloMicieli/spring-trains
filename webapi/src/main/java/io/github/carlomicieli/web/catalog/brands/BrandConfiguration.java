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

import io.github.carlomicieli.catalog.brands.BrandFactory;
import io.github.carlomicieli.catalog.brands.BrandId;
import io.github.carlomicieli.catalog.brands.queries.BrandQueriesRepository;
import io.github.carlomicieli.catalog.brands.queries.getbrandbyslug.GetBrandBySlugQuery;
import io.github.carlomicieli.catalog.brands.usecases.BrandUseCaseRepository;
import io.github.carlomicieli.catalog.brands.usecases.createbrand.CreateBrandInput;
import io.github.carlomicieli.catalog.brands.usecases.createbrand.CreateBrandOutputPort;
import io.github.carlomicieli.catalog.brands.usecases.createbrand.CreateBrandUseCase;
import io.github.carlomicieli.usecases.boundaries.input.UseCaseInputValidator;
import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrandConfiguration {

  @Bean
  public GetBrandBySlugQuery getBrandBySlugQuery(BrandQueriesRepository brandQueriesRepository) {
    return new GetBrandBySlugQuery(brandQueriesRepository);
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
