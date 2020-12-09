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
package io.github.carlomicieli.scales;

import io.github.carlomicieli.domain.AggregateRoot;
import io.github.carlomicieli.util.Slug;
import java.time.Instant;
import lombok.*;

/**
 * A model trains <em>Scale</em> is the relationship between its size and the size of an actual
 * train, usually measured as a ratio or as a millimetre to inch conversion. OO scale is said to be
 * 4mm:ft or 1:76.
 *
 * <p>A model trains <em>Gauge</em> is the distance between the inner edges of the two rails that it
 * runs on.
 */
@Data
@Builder
@With
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public final class Scale implements AggregateRoot<ScaleId> {
  private final ScaleId id;
  private final Slug slug;
  private final String name;
  private final Ratio ratio;
  private final ScaleGauge gauge;
  private final String description;
  private final Integer weight;
  private final int version;
  private final Instant createdDate;
  private final Instant modifiedDate;

  public static Slug buildSlug(String name) {
    return Slug.of(name);
  }
}
