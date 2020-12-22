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
package io.github.carlomicieli.catalog.brands.usecases.createbrand;

import static org.mockito.Mockito.*;

import io.github.carlomicieli.catalog.brands.Brand;
import io.github.carlomicieli.catalog.brands.BrandFactory;
import io.github.carlomicieli.catalog.brands.BrandId;
import io.github.carlomicieli.catalog.brands.usecases.BrandUseCaseRepository;
import io.github.carlomicieli.usecases.boundaries.input.UseCaseBeanValidator;
import io.github.carlomicieli.util.Slug;
import io.github.carlomicieli.validation.ValidationError;
import java.time.Clock;
import java.util.List;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Create brand use case")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CreateBrandUseCaseTest {

  private final BrandUseCaseRepository repository;
  private final CreateBrandOutputPort outputPort;
  private final CreateBrandUseCase useCase;

  public CreateBrandUseCaseTest(
      @Mock CreateBrandOutputPort outputPort, @Mock BrandUseCaseRepository repository) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    var useCaseValidator = new UseCaseBeanValidator<CreateBrandInput>(factory.getValidator());

    var brandFactory = new BrandFactory(Clock.systemUTC(), BrandId::randomId);

    this.repository = repository;
    this.outputPort = outputPort;

    this.useCase = new CreateBrandUseCase(useCaseValidator, outputPort, brandFactory, repository);
  }

  @Test
  void should_always_validate_input() {
    List<ValidationError> expected =
        List.of(
            ValidationError.of("brandKind", "must not be blank"),
            ValidationError.of("name", "must not be blank"));

    var input = CreateBrandInput.builder().build();

    useCase.execute(input);

    verify(outputPort, times(1)).invalidRequest(expected);
  }

  @Test
  void should_check_if_a_brand_with_the_same_name_already_exists() {
    when(repository.exists(Slug.of("ACME"))).thenReturn(true);

    var input = CreateBrandInput.builder().name("ACME").brandKind("industrial").build();

    useCase.execute(input);

    verify(outputPort, times(1)).brandAlreadyExists("ACME");
  }

  @Test
  @SneakyThrows
  void should_create_a_new_brand() {

    var input =
        CreateBrandInput.builder()
            .name("ACME")
            .companyName("Assiociazione costruzioni modellistiche esatte")
            .description("Description goes here")
            .brandKind("industrial")
            .mailAddress("mail@acmetreni.com")
            .websiteUrl("https://www.acmetreni.com")
            .build();

    useCase.execute(input);

    verify(repository, times(1)).save(isA(Brand.class));
    verify(outputPort, times(1)).standard(CreateBrandOutput.of(Slug.of("ACME")));
  }
}
