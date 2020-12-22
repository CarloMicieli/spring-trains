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
package io.github.carlomicieli.catalog.scales.queries.getscalebyslug;

import static org.assertj.core.api.Assertions.*;

import io.github.carlomicieli.catalog.scales.queries.ScaleQueriesRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Get scale by slug query")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class GetScaleBySlugQueryTest {
  private final GetScaleBySlugQuery query;
  private final ScaleQueriesRepository repo;

  public GetScaleBySlugQueryTest(@Mock ScaleQueriesRepository repo) {
    this.repo = repo;
    this.query = new GetScaleBySlugQuery(repo);
  }

  @Test
  void implement_this_test() {
    assertThat(query).isNotNull();
    assertThat(repo).isNotNull();
  }
}
