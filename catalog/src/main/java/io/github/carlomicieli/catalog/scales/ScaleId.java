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
package io.github.carlomicieli.catalog.scales;

import io.github.carlomicieli.domain.Identifier;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

/** The Scale unique identifier. */
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode
public final class ScaleId implements Identifier {
  private final UUID value;

  public static ScaleId randomId() {
    return new ScaleId(UUID.randomUUID());
  }

  @Override
  public UUID toUUID() {
    return value;
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
