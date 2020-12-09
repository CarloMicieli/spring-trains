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
package io.github.carlomicieli.persistence.common.adapter;

import io.github.carlomicieli.queries.pagination.Page;
import io.github.carlomicieli.queries.pagination.PaginatedResult;
import io.github.carlomicieli.queries.sorting.Sorting;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageableAdapter {
  public static Pageable toPageable(Page page, Sorting sort) {
    return PageRequest.of(0, page.getLimit());
  }

  public static <T, U> PaginatedResult<U> toPaginatedResult(
      Page currentPage,
      org.springframework.data.domain.Page<T> page,
      Function<T, U> mappingFunction) {
    return PaginatedResult.of(
        currentPage, page.stream().map(mappingFunction).collect(Collectors.toList()));
  }
}
