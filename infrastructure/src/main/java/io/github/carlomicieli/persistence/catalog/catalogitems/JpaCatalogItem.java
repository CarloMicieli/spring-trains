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
import io.github.carlomicieli.persistence.common.converter.DeliveryDateConverter;
import io.github.carlomicieli.persistence.common.converter.ItemNumberConverter;
import io.github.carlomicieli.persistence.common.converter.SlugConverter;
import io.github.carlomicieli.util.Slug;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "catalog_items")
@Builder
@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class JpaCatalogItem {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "catalog_item_id")
  UUID id;

  @OneToOne()
  @JoinColumn(name = "scale_id", referencedColumnName = "scale_id")
  JpaScale scale;

  @OneToOne()
  @JoinColumn(name = "brand_id", referencedColumnName = "brand_id")
  JpaBrand brand;

  @OneToMany(mappedBy = "catalogItem", cascade = CascadeType.ALL)
  Set<JpaRollingStock> rollingStocks;

  @Column(name = "item_number")
  @Convert(converter = ItemNumberConverter.class)
  ItemNumber itemNumber;

  @Convert(converter = SlugConverter.class)
  Slug slug;

  @Column(name = "power_method")
  @Enumerated(EnumType.STRING)
  PowerMethod powerMethod;

  @Column(name = "delivery_date")
  @Convert(converter = DeliveryDateConverter.class)
  DeliveryDate deliveryDate;

  boolean available;

  String description;

  @Column(name = "model_description")
  String modelDescription;

  @Column(name = "prototype_description")
  String prototypeDescription;

  @Column(name = "created")
  @CreatedDate
  Instant createdDate;

  @Column(name = "last_modified")
  @LastModifiedDate
  Instant modifiedDate;

  @Version int version;
}
