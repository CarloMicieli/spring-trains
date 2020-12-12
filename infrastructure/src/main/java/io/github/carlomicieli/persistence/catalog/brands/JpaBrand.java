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

import io.github.carlomicieli.brands.BrandKind;
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

@Entity
@Table(name = "brands")
@Builder
@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class JpaBrand {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "brand_id")
  UUID id;

  String name;

  @Column(name = "company_name")
  String companyName;

  @Column(name = "brand_logo_id")
  UUID logoId;

  @Convert(converter = SlugConverter.class)
  Slug slug;

  @Column(name = "website_url")
  @Convert(converter = URLConverter.class)
  URL websiteUrl;

  @Column(name = "group_name")
  String groupName;

  String description;

  @Embedded JpaAddress address;

  @Column(name = "kind")
  @Enumerated(EnumType.STRING)
  BrandKind brandKind;

  @Column(name = "email")
  @Convert(converter = MailAddressConverter.class)
  MailAddress mailAddress;

  @Column(name = "created")
  @CreatedDate
  Instant createdDate;

  @Column(name = "last_modified")
  @LastModifiedDate
  Instant modifiedDate;

  @Version int version;
}
