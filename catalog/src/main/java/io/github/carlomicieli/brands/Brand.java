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
package io.github.carlomicieli.brands;

import io.github.carlomicieli.addresses.Address;
import io.github.carlomicieli.mail.MailAddress;
import io.github.carlomicieli.util.Slug;
import java.net.URI;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.With;

@Data
@AllArgsConstructor
@Builder
@With
@Entity(name = "brands")
public class Brand {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private final UUID id;

  private final String name;

  private final String companyName;

  private final Slug slug;

  private final URI websiteUrl;

  private final String groupName;

  private final String description;

  private final Address address;

  private final BrandKind brandKind;

  private final MailAddress mailAddress;
}
