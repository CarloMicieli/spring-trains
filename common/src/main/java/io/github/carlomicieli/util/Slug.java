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
package io.github.carlomicieli.util;

import java.text.Normalizer;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * It converts a string to a "slug".
 *
 * @see <a href="http://www.codecodex.com/wiki/Generate_a_url_slug#Java">Original implementation</a>
 * @author Carlo Micieli
 */
public interface Slug {
  Pattern NON_LATIN = Pattern.compile("[^\\w-]");
  Pattern WHITESPACE = Pattern.compile("[\\s]");
  String SEP = "-";

  /**
   * Checks whether the provided {@code slugValue} is not empty, otherwise the method will use the
   * {@code supplier} function to produce a value.
   *
   * @param slugValue the (possible empty or {@code null} slug value
   * @param supplier the slug supplier
   * @return the slug
   */
  static String orElseGet(String slugValue, Supplier<String> supplier) {
    if (slugValue != null && !slugValue.isEmpty()) {
      return slugValue;
    }

    try {
      return supplier.get();
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * Encodes the provided String as {@code slug}.
   *
   * @param str the String to be encoded
   * @return the slug
   */
  static String of(String str) {
    Objects.requireNonNull(str, "Slug: input string must be not null");
    String noWhitespace = WHITESPACE.matcher(str).replaceAll(SEP);
    String normalized = Normalizer.normalize(noWhitespace, Normalizer.Form.NFD);
    return NON_LATIN.matcher(normalized).replaceAll("").toLowerCase(Locale.ENGLISH);
  }

  /**
   * Joins the provided values and encode them as {@code slug}.
   *
   * @param values the values to be joined and then encoded
   * @return the slug
   */
  static String ofValues(Object... values) {
    Objects.requireNonNull(values, "Slug: input values must be not null");
    Predicate<Object> valueIsNotNull = obj -> !Objects.isNull(obj);
    return Stream.of(values)
        .filter(valueIsNotNull)
        .map(Object::toString)
        .map(Slug::of)
        .collect(Collectors.joining(SEP));
  }
}
