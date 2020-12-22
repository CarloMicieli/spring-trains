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
package io.github.carlomicieli.persistence.catalog.scales;

import io.github.carlomicieli.catalog.scales.Ratio;
import io.github.carlomicieli.catalog.scales.ScaleGauge;
import io.github.carlomicieli.persistence.common.converter.GaugeConverter;
import io.github.carlomicieli.persistence.common.converter.RatioConverter;
import io.github.carlomicieli.persistence.common.converter.SlugConverter;
import io.github.carlomicieli.persistence.common.converter.TrackGaugeConverter;
import io.github.carlomicieli.util.Slug;
import java.time.Instant;
import java.util.UUID;
import javax.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;

@Entity
@Table(name = "scales")
@Builder
@Data
@With
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class JpaScale implements Persistable<UUID> {
  @Column(name = "scale_id")
  @Id
  private UUID id;

  @Convert(converter = SlugConverter.class)
  private Slug slug;

  private String name;

  @Convert(converter = RatioConverter.class)
  private Ratio ratio;

  @Embedded
  @Converts({
    @Convert(attributeName = "millimetres", converter = GaugeConverter.MILLIMETERS.class),
    @Convert(attributeName = "inches", converter = GaugeConverter.INCHES.class),
    @Convert(attributeName = "trackGauge", converter = TrackGaugeConverter.class)
  })
  @AttributeOverrides({
    @AttributeOverride(name = "millimetres", column = @Column(name = "gauge_mm")),
    @AttributeOverride(name = "inches", column = @Column(name = "gauge_in")),
    @AttributeOverride(name = "trackGauge", column = @Column(name = "track_type"))
  })
  private ScaleGauge gauge;

  private String standards;

  private String description;

  private Integer weight;

  @Column(name = "created")
  @CreatedDate
  private Instant createdDate;

  @Column(name = "last_modified")
  @LastModifiedDate
  private Instant modifiedDate;

  @Version private int version;

  @Override
  public boolean isNew() {
    return version == 1;
  }
}
