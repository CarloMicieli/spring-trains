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
package io.github.carlomicieli.catalogitems.rollingstocks;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ServiceLevel {
  private final String value;

  public static final ServiceLevel FirstClass = new ServiceLevel("1cl");
  public static final ServiceLevel SecondClass = new ServiceLevel("2cl");
  public static final ServiceLevel FirstSecondClass = new ServiceLevel("1cl/2cl");
  public static final ServiceLevel ThirdClass = new ServiceLevel("3cl");
  public static final ServiceLevel SecondThirdClass = new ServiceLevel("2cl/3cl");

  private static final Map<String, ServiceLevel> cachedValues;

  static {
    cachedValues =
        Map.ofEntries(
            Map.entry(FirstClass.toString().toLowerCase(), FirstClass),
            Map.entry(SecondClass.toString().toLowerCase(), SecondClass),
            Map.entry(FirstSecondClass.toString().toLowerCase(), FirstSecondClass),
            Map.entry(ThirdClass.toString().toLowerCase(), ThirdClass),
            Map.entry(SecondThirdClass.toString().toLowerCase(), SecondThirdClass));
  }

  public static ServiceLevel parse(String str) {
    throw new UnsupportedOperationException();
  }
}
