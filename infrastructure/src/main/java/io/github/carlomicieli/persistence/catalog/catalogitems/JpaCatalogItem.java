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

import io.github.carlomicieli.catalogitems.ItemNumber;
import io.github.carlomicieli.catalogitems.PowerMethod;
import io.github.carlomicieli.catalogitems.deliverydates.DeliveryDate;
import io.github.carlomicieli.persistence.catalog.brands.JpaBrand;
import io.github.carlomicieli.persistence.catalog.scales.JpaScale;
import io.github.carlomicieli.persistence.common.converter.*;
import io.github.carlomicieli.util.Slug;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;

@Entity
@Table(name = "catalog_items")
@Builder
@Data
@With
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class JpaCatalogItem implements Persistable<UUID> {
  @Id
  @Column(name = "catalog_item_id")
  private UUID id;

  @OneToOne()
  @JoinColumn(name = "scale_id", referencedColumnName = "scale_id")
  private JpaScale scale;

  @OneToOne()
  @JoinColumn(name = "brand_id", referencedColumnName = "brand_id")
  private JpaBrand brand;

  @OneToMany(mappedBy = "catalogItem", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<JpaRollingStock> rollingStocks;

  @Column(name = "item_number")
  @Convert(converter = ItemNumberConverter.class)
  private ItemNumber itemNumber;

  @Convert(converter = SlugConverter.class)
  private Slug slug;

  @Column(name = "power_method")
  @Enumerated(EnumType.STRING)
  private PowerMethod powerMethod;

  @Column(name = "delivery_date")
  @Convert(converter = DeliveryDateConverter.class)
  private DeliveryDate deliveryDate;

  private boolean available;

  private String description;

  @Column(name = "model_description")
  private String modelDescription;

  @Column(name = "prototype_description")
  private String prototypeDescription;

  @Column(name = "created")
  @CreatedDate
  private Instant createdDate;

  @Column(name = "last_modified")
  @LastModifiedDate
  private Instant modifiedDate;

  @Version private int version;

  public void addRollingStock(JpaRollingStock rs) {
    if (rollingStocks == null) {
      rollingStocks = new ArrayList<>();
    }
    rollingStocks.add(rs);
    rs.setCatalogItem(this);
  }

  public void removeComment(JpaRollingStock rs) {
    if (rollingStocks == null) {
      return;
    }

    rollingStocks.remove(rs);
    rs.setCatalogItem(null);
  }

  @Override
  public boolean isNew() {
    return version == 1;
  }
}
