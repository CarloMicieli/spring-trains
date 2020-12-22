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
package io.github.carlomicieli.catalog.brands.queries.getbrandbyslug;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

import io.github.carlomicieli.catalog.brands.Brand;
import io.github.carlomicieli.catalog.brands.queries.BrandQueriesRepository;
import io.github.carlomicieli.util.Slug;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Get brand by slug query")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class GetBrandBySlugQueryTest {

  private final GetBrandBySlugQuery query;
  private final BrandQueriesRepository repository;

  public GetBrandBySlugQueryTest(@Mock BrandQueriesRepository repo) {
    this.repository = repo;
    this.query = new GetBrandBySlugQuery(repo);
  }

  @Test
  void is_should_return_empty_result_when_the_brand_does_not_exist() {
    var criteria = new GetBrandBySlugCriteria(Slug.of("not found"));

    var result = query.execute(criteria);

    assertThat(result).isNotNull();
    assertThat(result.isEmpty()).isTrue();
  }

  @Test
  void is_should_return_a_result_when_the_brand_exists() {
    var slug = Slug.of("ACME");
    when(repository.findBySlug(slug)).thenReturn(Optional.of(Brand.builder().name("ACME").build()));

    var criteria = new GetBrandBySlugCriteria(slug);

    var result = query.execute(criteria);

    assertThat(result).isNotNull();
    assertThat(result.isPresent()).isTrue();
    assertThat(result.get().getName()).isEqualTo("ACME");
  }
}
