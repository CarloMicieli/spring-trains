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
package io.github.carlomicieli.domain;

import java.time.Instant;
import lombok.Getter;
import org.apache.commons.lang3.Validate;

/**
 * An aggregate root.
 *
 * @param <ID> the ID type
 */
@Getter
public abstract class AggregateRoot<ID> extends Entity<ID> {

  private final Instant createdDate;

  private final Instant modifiedDate;

  private final int version;

  protected AggregateRoot(ID id, Instant createdDate, Instant modifiedDate, int version) {
    super(id);

    Validate.isTrue(version >= 0, "Aggregate version must be non negative");
    this.createdDate = createdDate;
    this.modifiedDate = modifiedDate;
    this.version = version;
  }
}
