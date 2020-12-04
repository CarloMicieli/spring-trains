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
package io.github.carlomicieli.addresses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.With;
import org.apache.commons.lang3.StringUtils;

/** An immutable object value that represents an {@code Address}. */
@Data
@AllArgsConstructor
@Builder
@With
public class Address {
  private final String line1;
  private final String line2;
  private final String city;
  private final String region;
  private final String postalCode;
  private final String country;

  /**
   * Checks whether the provided {@code address} is empty.
   *
   * <p>An {@code address} is empty if it doesn't contain a valid value for at least one of the
   * following fields: {@code streetAddress}, {@code postalCode}, {@code city} or {@code country}.
   *
   * @param address the address to be checked
   * @return {@code true} if the {@code Address} is empty, {@code false} otherwise.
   */
  public static boolean isEmpty(Address address) {
    if (address == null) {
      return true;
    }

    return StringUtils.isBlank(address.line1)
        || StringUtils.isBlank(address.postalCode)
        || StringUtils.isBlank(address.city)
        || StringUtils.isBlank(address.country);
  }

  public static Address NULL_ADDRESS = new Address("", null, "", null, "", "");
}
