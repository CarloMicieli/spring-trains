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
package io.github.carlomicieli.persistence.common.converter;

import com.google.common.base.Strings;
import io.github.carlomicieli.catalogitems.Epoch;
import javax.persistence.AttributeConverter;

public class EpochConverter implements AttributeConverter<Epoch, String> {
  @Override
  public String convertToDatabaseColumn(Epoch epoch) {
    if (epoch == null) {
      return null;
    }

    return epoch.toString();
  }

  @Override
  public Epoch convertToEntityAttribute(String s) {
    if (Strings.isNullOrEmpty(s)) {
      return null;
    }

    return Epoch.parse(s);
  }
}
