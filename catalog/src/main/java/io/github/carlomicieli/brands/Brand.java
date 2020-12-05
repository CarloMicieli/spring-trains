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
import io.github.carlomicieli.domain.AggregateRoot;
import io.github.carlomicieli.mail.MailAddress;
import io.github.carlomicieli.util.Slug;
import java.net.URI;
import java.time.Instant;
import lombok.*;

/**
 * It represents a model railways rolling stock manufacturer.
 *
 * <p>Two distinct family of manufacturer exists:
 *
 * <ul>
 *   <li>{@code industrial}: these manufactures produce models using the die casting method;
 *   <li>{@code brass models}: these manufacturers produce models which are made of brass or similar
 *       alloys. They are usually more expensive than the industrial series due to the limited
 *       production quantities and the {@code "hand made"} nature of the production.
 * </ul>
 *
 * <p>This class allows one company to have local branches for a given country, either a local
 * office or a distribution company.
 */
@Data
@Builder
@With
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public final class Brand implements AggregateRoot<BrandId> {
  private final BrandId id;
  private final String name;
  private final String companyName;
  private final Slug slug;
  private final URI websiteUrl;
  private final String groupName;
  private final String description;
  private final Address address;
  private final BrandKind brandKind;
  private final MailAddress mailAddress;
  private final int version;
  private final Instant createdDate;
  private final Instant modifiedDate;

  protected static Slug buildSlug(String name) {
    return Slug.of(name);
  }
}
