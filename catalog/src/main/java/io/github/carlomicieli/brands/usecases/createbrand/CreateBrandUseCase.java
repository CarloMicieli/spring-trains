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
package io.github.carlomicieli.brands.usecases.createbrand;

import io.github.carlomicieli.addresses.Address;
import io.github.carlomicieli.brands.BrandFactory;
import io.github.carlomicieli.brands.BrandKind;
import io.github.carlomicieli.brands.usecases.BrandUseCaseRepository;
import io.github.carlomicieli.mail.MailAddress;
import io.github.carlomicieli.usecases.AbstractUseCase;
import io.github.carlomicieli.usecases.boundaries.input.UseCaseInputValidator;
import io.github.carlomicieli.util.EnumUtils;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

@Log4j2
public final class CreateBrandUseCase
    extends AbstractUseCase<CreateBrandInput, CreateBrandOutput, CreateBrandOutputPort> {
  private final BrandUseCaseRepository brandsRepo;
  private final BrandFactory brandFactory;

  public CreateBrandUseCase(
      UseCaseInputValidator<CreateBrandInput> inputValidator,
      CreateBrandOutputPort outputPort,
      BrandFactory brandFactory,
      BrandUseCaseRepository repo) {
    super(inputValidator, outputPort);
    this.brandsRepo = Objects.requireNonNull(repo);
    this.brandFactory = Objects.requireNonNull(brandFactory);
  }

  @Override
  protected void handle(CreateBrandInput input) {
    log.debug("Creating new brand {}", input);

    var mailAddress = MailAddress.tryParse(input.getMailAddress());
    var brandKind = EnumUtils.parseEnum(BrandKind.class, input.getBrandKind());
    var websiteUrl = tryParseUrl(input.getWebsiteUrl());

    Address address = null;
    if (input.getAddress() != null) {
      var a = input.getAddress();
      address =
          Address.builder()
              .line1(a.getLine1())
              .line2(a.getLine2())
              .country(a.getCountry())
              .city(a.getCity())
              .postalCode(a.getPostalCode())
              .region(a.getRegion())
              .build();
    }

    var brand =
        brandFactory.createNewBrand(
            input.getName(),
            input.getCompanyName(),
            websiteUrl.orElse(null),
            input.getGroupName(),
            input.getDescription(),
            address,
            brandKind,
            mailAddress.orElse(null));

    boolean exists = brandsRepo.exists(brand.getSlug());
    if (exists) {
      outputPort.brandAlreadyExists(input.getName());
      return;
    }

    brandsRepo.save(brand);
    outputPort.standard(CreateBrandOutput.of(brand.getSlug()));
  }

  private static Optional<URL> tryParseUrl(String url) {
    if (StringUtils.isBlank(url)) {
      return Optional.empty();
    }

    try {
      return Optional.of(new URL(url));
    } catch (MalformedURLException e) {
      return Optional.empty();
    }
  }
}
