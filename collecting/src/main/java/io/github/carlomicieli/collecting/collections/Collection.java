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
package io.github.carlomicieli.collecting.collections;

import io.github.carlomicieli.collecting.valueobject.Owner;
import io.github.carlomicieli.domain.AggregateRoot;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.With;

@Data
@AllArgsConstructor
@Builder
@With
public final class Collection implements AggregateRoot<CollectionId> {
  private final CollectionId id;
  private final Owner owner;
  private final String notes;
  private final List<CollectionItem> items = new ArrayList<>();
  private final Instant createdDate;
  private final Instant modifiedDate;
  private final int version;

  private List<CollectionItem> getItems() {
    return items;
  }

  public int size() {
    return items.size();
  }

  public void addItem(CollectionItem item) {
    items.add(item);
  }

  public void removeItem(CollectionItem item) {
    items.remove(item);
  }
}
