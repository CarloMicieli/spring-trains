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

import io.github.carlomicieli.countries.Country;
import io.github.carlomicieli.persistence.common.converter.CountryConverter;
import io.github.carlomicieli.persistence.common.converter.SlugConverter;
import io.github.carlomicieli.persistence.common.converter.URLConverter;
import io.github.carlomicieli.util.Slug;
import java.net.URL;
import java.time.Instant;
import java.util.UUID;
import javax.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "railways")
@Builder
@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class JpaRailway {
  @Column(name = "railway_id")
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
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

  @Embedded private JpaPeriodOfActivity periodOfActivity;

  @Embedded private JpaRailwayGauge trackGauge;

  @Embedded private JpaRailwayLength totalLength;

  @Convert(converter = URLConverter.class)
  @Column(name = "website_url")
  private URL websiteUrl;

  private String headquarters;

  @Column(name = "created")
  @CreatedDate
  private Instant createdDate;

  @Column(name = "last_modified")
  @LastModifiedDate
  private Instant modifiedDate;

  @Version private int version;
}
