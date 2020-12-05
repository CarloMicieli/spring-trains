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
package io.github.carlomicieli.countries;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * It represents an ISO country.
 *
 * <p>A simple wrapper around country code, to ensure it allows only valid country codes
 */
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class Country {

  private final String code;
  private final String englishName;

  public static Country of(String code) {
    throw new UnsupportedOperationException();

    //    if (code != null && code.length() != 2)
    //    {
    //      throw new IllegalArgumentException("Invalid country code");
    //    }
    //
    //
    //    return new Country(code, null);
  }
}
