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
package io.github.carlomicieli.collecting.wishlists;

import io.github.carlomicieli.collecting.valueobject.Owner;
import io.github.carlomicieli.domain.AggregateRoot;
import java.time.Instant;
import java.util.List;
import lombok.*;

@AllArgsConstructor
@Data
@Builder
@With
public final class Wishlist implements AggregateRoot<WishlistId> {
  private final WishlistId id;
  private final String name;
  private final Owner owner;
  private final Budget budget;
  private final List<WishlistItem> items;
  private final Instant createdDate;
  private final Instant modifiedDate;
  private final int version;
}
