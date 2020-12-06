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
package io.github.carlomicieli.brands.queries.getbrandslist;

import io.github.carlomicieli.brands.Brand;
import io.github.carlomicieli.brands.queries.BrandQueriesRepository;
import io.github.carlomicieli.queries.PaginatedQuery;
import io.github.carlomicieli.queries.pagination.Page;
import io.github.carlomicieli.queries.pagination.PaginatedResult;
import io.github.carlomicieli.queries.sorting.Sorting;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
public final class GetBrandsListQuery implements PaginatedQuery<Brand> {
  private final BrandQueriesRepository repository;

  @Override
  public PaginatedResult<Brand> execute(Page currentPage, Sorting orderBy) {
    return repository.findBrands(currentPage, orderBy);
  }
}
