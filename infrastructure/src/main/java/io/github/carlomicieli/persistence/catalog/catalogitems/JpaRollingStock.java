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

import io.github.carlomicieli.catalogitems.Control;
import io.github.carlomicieli.catalogitems.DccInterface;
import io.github.carlomicieli.catalogitems.Epoch;
import io.github.carlomicieli.catalogitems.rollingstocks.Category;
import io.github.carlomicieli.catalogitems.rollingstocks.Couplers;
import io.github.carlomicieli.catalogitems.rollingstocks.MinRadius;
import io.github.carlomicieli.catalogitems.rollingstocks.ServiceLevel;
import io.github.carlomicieli.persistence.catalog.railways.JpaRailway;
import io.github.carlomicieli.persistence.common.converter.EpochConverter;
import io.github.carlomicieli.persistence.common.converter.MinRadiusConverter;
import io.github.carlomicieli.persistence.common.converter.ServiceLevelConverter;
import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "rolling_stocks")
@Builder
@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class JpaRollingStock {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "rolling_stock_id")
  UUID id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "catalog_item_id", referencedColumnName = "catalog_item_id")
  JpaCatalogItem catalogItem;

  @OneToOne()
  @JoinColumn(name = "railway_id", referencedColumnName = "railway_id")
  JpaRailway railway;

  @Enumerated(EnumType.STRING)
  Category category;

  @Column(name = "sub_category")
  String subCategory;

  @Convert(converter = EpochConverter.class)
  Epoch epoch;

  @Convert(converter = MinRadiusConverter.class)
  MinRadius minRadius;

  @Enumerated(EnumType.STRING)
  Couplers couplers;

  String livery;

  @Column(name = "length_mm")
  BigDecimal lengthMillimeters;

  @Column(name = "length_in")
  BigDecimal lengthInches;

  @Column(name = "type_name")
  String typeName;

  @Column(name = "road_number")
  String roadNumber;

  String series;

  String depot;

  @Column(name = "dcc_interface")
  @Enumerated(EnumType.STRING)
  DccInterface dccInterface;

  @Enumerated(EnumType.STRING)
  Control control;

  @Column(name = "service_level")
  @Convert(converter = ServiceLevelConverter.class)
  ServiceLevel serviceLevel;
}
