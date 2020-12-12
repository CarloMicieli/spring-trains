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
package io.github.carlomicieli.persistence.catalog.catalogitems;

import io.github.carlomicieli.persistence.images.JpaImage;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "catalog_items_images")
@Builder
@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class JpaCatalogItemImage {

  @EmbeddedId JpaCatalogItemImageKey id;

  @ManyToOne
  @MapsId("image_id")
  @JoinColumn(name = "image_id")
  JpaImage image;

  @ManyToOne
  @MapsId("catalog_item_id")
  @JoinColumn(name = "catalog_item_id")
  JpaCatalogItem catalogItem;

  @Column(name = "is_default")
  boolean isDefault;

  @Embeddable
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  static class JpaCatalogItemImageKey implements Serializable {

    @Column(name = "image_id")
    UUID imageId;

    @Column(name = "catalog_item_id")
    UUID catalogItemId;
  }
}
