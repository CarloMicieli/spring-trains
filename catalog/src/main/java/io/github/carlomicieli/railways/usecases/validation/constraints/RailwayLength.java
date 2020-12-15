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
package io.github.carlomicieli.railways.usecases.validation.constraints;

import static java.lang.annotation.ElementType.*;

import io.github.carlomicieli.railways.usecases.validation.RailwayLengthValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {FIELD, METHOD, PARAMETER})
@Constraint(validatedBy = RailwayLengthValidator.class)
@Documented
public @interface RailwayLength {
  String message() default "{com.trenako.railway.length.invalid}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
