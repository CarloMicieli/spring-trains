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

import io.github.carlomicieli.brands.Brand;
import io.github.carlomicieli.brands.usecases.BrandUseCaseRepository;
import io.github.carlomicieli.util.Slug;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class JpaBrandUseCaseRepository implements BrandUseCaseRepository {
  private final JpaBrandRepository jpaRepo;

  @Override
  public boolean exists(Slug brandSlug) {
    return jpaRepo.existsBySlug(brandSlug);
  }

  @Override
  public void save(Brand brand) {
    jpaRepo.save(
        JpaBrand.builder()
            .id(brand.getId().toUUID())
            .name(brand.getName())
            .slug(brand.getSlug())
            .brandKind(brand.getBrandKind())
            .address(JpaAddress.fromDomain(brand.getAddress()))
            .mailAddress(brand.getMailAddress())
            .description(brand.getDescription())
            .groupName(brand.getGroupName())
            .websiteUrl(brand.getWebsiteUrl())
            .version(brand.getVersion())
            .createdDate(brand.getCreatedDate())
            .modifiedDate(brand.getModifiedDate())
            .build());
  }
}
