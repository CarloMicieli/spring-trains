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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("A Slug")
class SlugTest {
  @Test
  void shouldEncodeEmptyStringAsSlugs() {
    String slug = Slug.of("");
    assertThat(slug).isEmpty();
  }

  @Test
  public void shouldEncodeWhitespacesAsSlugs() {
    String slug = Slug.of("Time is an illusion");
    assertThat(slug).isEqualTo("time-is-an-illusion");
  }

  @Test
  public void shouldEncodePunctuationSignsAsSlugs() {
    String slug = Slug.of("Time; is an: illusion.");
    assertThat(slug).isEqualTo("time-is-an-illusion");
  }

  @Test
  public void shouldEncodeNonLatinCharactersAsSlugs() {
    String slug = Slug.of("Timè is àn illusiòn.");
    assertThat(slug).isEqualTo("time-is-an-illusion");
  }

  //    @Test
  //    public void shouldThrowExceptionIfProvidedStringIsNull() {
  //        expect(() -> Slug.of(null))
  //                .toThrow(NullPointerException.class, Matchers.is("Slug: input string must be not
  // null"));
  //    }

  @Test
  public void shouldEncodeMultipleValuesAsSlugs() {
    String slug = Slug.ofValues("first", 2, null, "end");
    assertThat(slug).isEqualTo("first-2-end");
  }

  //    @Test
  //    public void shouldThrowExceptionIfProvidedValuesAreNull() {
  //        expect(() -> Slug.ofValues((Object[])null))
  //                .toThrow(NullPointerException.class, Matchers.is("Slug: input values must be not
  // null"));
  //    }

  @Test
  public void shouldCheckWhetherSlugIsNotEmptyAndIfSoUseTheProvidedSupplier() {
    Supplier<String> slugSupplier = () -> "default-slug";
    assertThat(Slug.orElseGet("my-slug", slugSupplier)).isEqualTo("my-slug");
    assertThat(Slug.orElseGet("", slugSupplier)).isEqualTo("default-slug");
    assertThat(Slug.orElseGet(null, slugSupplier)).isEqualTo("default-slug");
    assertThat(Slug.orElseGet(null, () -> null)).isNull();
  }
}
