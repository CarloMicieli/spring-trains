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
package io.github.carlomicieli.catalogitems;

import io.github.carlomicieli.catalogitems.deliverydates.DeliveryDate;
import io.github.carlomicieli.catalogitems.rollingstocks.RollingStock;
import io.github.carlomicieli.domain.AggregateRoot;
import io.github.carlomicieli.util.Slug;
import java.time.Instant;
import java.util.List;
import java.util.stream.Stream;
import lombok.*;

@Data
@Builder
@With
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public final class CatalogItem implements AggregateRoot<CatalogItemId> {

  private final CatalogItemId id;
  private final BrandRef brand;
  private final ScaleRef scale;
  private final ItemNumber itemNumber;
  private final Slug slug;
  private final String category;
  private final String description;
  private final String prototypeDescription;
  private final String modelDescription;
  private final List<RollingStock> rollingStocks;
  private final PowerMethod powerMethod;
  private final DeliveryDate deliveryDate;
  private final boolean available;
  private final int version;
  private final Instant createdDate;
  private final Instant modifiedDate;

  /** Returns the number of rolling stocks for this catalog item */
  public int getCount() {
    throw new UnsupportedOperationException();
  }

  /**
   * Returns the category for this catalog item, from the categories of the rolling stocks included
   */
  public CatalogItemCategory getCategory() {
    throw new UnsupportedOperationException();
  }

  public Stream<RollingStock> getRollingStocks() {
    return rollingStocks.stream();
  }
}
