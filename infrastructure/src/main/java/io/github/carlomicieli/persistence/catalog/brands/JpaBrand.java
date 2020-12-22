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
package io.github.carlomicieli.persistence.catalog.brands;

import io.github.carlomicieli.addresses.Address;
import io.github.carlomicieli.catalog.brands.BrandKind;
import io.github.carlomicieli.mail.MailAddress;
import io.github.carlomicieli.persistence.common.converter.MailAddressConverter;
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
import org.springframework.data.domain.Persistable;

@Entity
@Table(name = "brands")
@Builder
@Data
@With
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class JpaBrand implements Persistable<UUID> {
  @Id
  @Column(name = "brand_id")
  private UUID id;

  private String name;

  @Column(name = "company_name")
  private String companyName;

  @Column(name = "brand_logo_id")
  private UUID logoId;

  @Convert(converter = SlugConverter.class)
  private Slug slug;

  @Column(name = "website_url")
  @Convert(converter = URLConverter.class)
  private URL websiteUrl;

  @Column(name = "group_name")
  private String groupName;

  private String description;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "line1", column = @Column(name = "address_line1")),
    @AttributeOverride(name = "line2", column = @Column(name = "address_line2")),
    @AttributeOverride(name = "city", column = @Column(name = "address_city")),
    @AttributeOverride(name = "region", column = @Column(name = "address_region")),
    @AttributeOverride(name = "postalCode", column = @Column(name = "address_postal_code")),
    @AttributeOverride(name = "country", column = @Column(name = "address_country"))
  })
  private Address address;

  @Column(name = "kind")
  @Enumerated(EnumType.STRING)
  private BrandKind brandKind;

  @Column(name = "email")
  @Convert(converter = MailAddressConverter.class)
  private MailAddress mailAddress;

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
