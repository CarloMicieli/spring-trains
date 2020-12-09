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

import static org.assertj.core.api.Assertions.*;

import io.github.carlomicieli.addresses.Address;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayName("JPA Address")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class JpaAddressTest {

  @Test
  void it_is_converted_from_domain_address() {
    var address =
        Address.builder()
            .line1("Acacia Avenue 22")
            .line2("Apartment 1")
            .region("West End")
            .country("GB")
            .city("London")
            .postalCode("123456")
            .build();

    var jpaAddress = JpaAddress.fromDomain(address);

    assertThat(jpaAddress).isNotNull();
    assertThat(jpaAddress.line1).isEqualTo(address.getLine1());
    assertThat(jpaAddress.line2).isEqualTo(address.getLine2());
    assertThat(jpaAddress.region).isEqualTo(address.getRegion());
    assertThat(jpaAddress.country).isEqualTo(address.getCountry());
    assertThat(jpaAddress.city).isEqualTo(address.getCity());
    assertThat(jpaAddress.postalCode).isEqualTo(address.getPostalCode());
  }

  @Test
  void it_is_converted_to_domain_address() {
    var jpaAddress =
        JpaAddress.builder()
            .line1("Acacia Avenue 22")
            .line2("Apartment 1")
            .region("West End")
            .country("GB")
            .city("London")
            .postalCode("123456")
            .build();

    var address = jpaAddress.toDomain();

    assertThat(address).isNotNull();
    assertThat(address.getLine1()).isEqualTo(jpaAddress.line1);
    assertThat(address.getLine2()).isEqualTo(jpaAddress.line2);
    assertThat(address.getRegion()).isEqualTo(jpaAddress.region);
    assertThat(address.getCountry()).isEqualTo(jpaAddress.country);
    assertThat(address.getCity()).isEqualTo(jpaAddress.city);
    assertThat(address.getPostalCode()).isEqualTo(jpaAddress.postalCode);
  }
}
