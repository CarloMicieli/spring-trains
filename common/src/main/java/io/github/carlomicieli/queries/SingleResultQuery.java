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
package io.github.carlomicieli.queries;

import java.util.Optional;

/**
 * A query that can produce one or zero results.
 *
 * @param <C> the criteria data type
 * @param <T> the view model data type
 */
public interface SingleResultQuery<C extends Criteria, T> extends Query<C, T> {
  Optional<T> execute(C criteria);
}
