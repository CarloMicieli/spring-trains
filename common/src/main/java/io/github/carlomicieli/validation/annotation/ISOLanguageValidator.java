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
package io.github.carlomicieli.validation.annotation;

import io.github.carlomicieli.countries.ISOValidationUtils;
import io.github.carlomicieli.validation.annotation.constraints.ISOLanguage;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/** It represents a validator for languages ISO codes. */
public final class ISOLanguageValidator implements ConstraintValidator<ISOLanguage, String> {

  @Override
  public void initialize(ISOLanguage constraintAnnotation) {}

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return ISOValidationUtils.languageIsValid(value);
  }
}
