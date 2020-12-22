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
package io.github.carlomicieli.persistence.catalog.railways;

import io.github.carlomicieli.catalog.railways.PeriodOfActivity;
import io.github.carlomicieli.catalog.railways.RailwayGauge;
import io.github.carlomicieli.catalog.railways.RailwayLength;
import io.github.carlomicieli.countries.Country;
import io.github.carlomicieli.persistence.common.converter.*;
import io.github.carlomicieli.util.Slug;
import java.net.URL;
import java.time.Instant;
import java.util.UUID;
import javax.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;

@Entity
@Table(name = "railways")
@Builder
@Data
@With
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class JpaRailway implements Persistable<UUID> {
  @Column(name = "railway_id")
  @Id
  private UUID id;

  @Convert(converter = SlugConverter.class)
  private Slug slug;

  private String name;

  @Column(name = "railway_logo_id")
  private UUID logoId;

  @Column(name = "company_name")
  private String companyName;

  @Convert(converter = CountryConverter.class)
  private Country country;

  @Embedded
  @Converts({
    @Convert(attributeName = "railwayStatus", converter = RailwayStatusConverter.class),
  })
  @AttributeOverrides({
    @AttributeOverride(name = "railwayStatus", column = @Column(name = "active")),
    @AttributeOverride(
        name = "operatingSince",
        column = @Column(name = "operating_since", columnDefinition = "TIMESTAMP")),
    @AttributeOverride(
        name = "operatingUntil",
        column = @Column(name = "operating_until", columnDefinition = "TIMESTAMP"))
  })
  private PeriodOfActivity periodOfActivity;

  @Embedded
  @Converts({
    @Convert(attributeName = "millimeters", converter = LengthConverter.MILLIMETERS.class),
    @Convert(attributeName = "inches", converter = LengthConverter.INCHES.class),
    @Convert(attributeName = "trackGauge", converter = TrackGaugeConverter.class)
  })
  @AttributeOverrides({
    @AttributeOverride(name = "millimeters", column = @Column(name = "gauge_mm")),
    @AttributeOverride(name = "inches", column = @Column(name = "gauge_in")),
    @AttributeOverride(name = "trackGauge", column = @Column(name = "track_type"))
  })
  private RailwayGauge trackGauge;

  @Embedded
  @Converts({
    @Convert(attributeName = "kilometers", converter = LengthConverter.KILOMETERS.class),
    @Convert(attributeName = "miles", converter = LengthConverter.MILES.class)
  })
  @AttributeOverrides({
    @AttributeOverride(name = "kilometers", column = @Column(name = "total_length_km")),
    @AttributeOverride(name = "miles", column = @Column(name = "total_length_mi"))
  })
  private RailwayLength totalLength;

  @Convert(converter = URLConverter.class)
  @Column(name = "website_url")
  private URL websiteUrl;

  private String headquarters;

  private String description;

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
