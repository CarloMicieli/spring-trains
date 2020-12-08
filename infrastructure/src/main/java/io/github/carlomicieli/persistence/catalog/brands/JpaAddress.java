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

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class JpaAddress {

  @Column(name = "address_line1")
  String line1;

  @Column(name = "address_line2")
  String line2;

  @Column(name = "address_city")
  String city;

  @Column(name = "address_region")
  String region;

  @Column(name = "address_postal_code")
  String postalCode;

  @Column(name = "address_country")
  String country;
}
