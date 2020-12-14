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
import io.github.carlomicieli.catalogitems.rollingstocks.*;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class JpaRollingStock {
  @Id
  @Column(name = "rolling_stock_id")
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "catalog_item_id", nullable = false)
  private JpaCatalogItem catalogItem;

  @OneToOne()
  @JoinColumn(name = "railway_id", referencedColumnName = "railway_id")
  private JpaRailway railway;

  @Enumerated(EnumType.STRING)
  private Category category;

  @Column(name = "sub_category")
  private String subCategory;

  @Convert(converter = EpochConverter.class)
  private Epoch epoch;

  @Convert(converter = MinRadiusConverter.class)
  private MinRadius minRadius;

  @Enumerated(EnumType.STRING)
  private Couplers couplers;

  private String livery;

  @Column(name = "length_mm")
  private BigDecimal lengthMillimeters;

  @Column(name = "length_in")
  private BigDecimal lengthInches;

  @Column(name = "type_name")
  private String typeName;

  @Column(name = "road_number")
  private String roadNumber;

  private String series;

  private String depot;

  @Column(name = "dcc_interface")
  @Enumerated(EnumType.STRING)
  private DccInterface dccInterface;

  @Enumerated(EnumType.STRING)
  private Control control;

  @Column(name = "service_level")
  @Convert(converter = ServiceLevelConverter.class)
  private ServiceLevel serviceLevel;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof JpaRollingStock)) return false;
    return id != null && id.equals(((JpaRollingStock) o).getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
