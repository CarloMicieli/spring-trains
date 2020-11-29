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
package io.github.carlomicieli.mail;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayName("A mail address")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MailAddressTest {

  @Test
  void it_is_created_from_its_address() {
    final String expected = "mail@mail.com";

    var mail = new MailAddress("mail@mail.com");
    assertThat(mail, notNullValue());
    assertThat(mail.getAddress(), equalTo(expected));
  }

  @Test
  void it_should_accept_only_valid_addresses() {
    IllegalArgumentException thrown =
        assertThrows(
            IllegalArgumentException.class,
            () -> new MailAddress("invalid mail"),
            "Expected new MailAddress() to throw, but it didn't");

    assertThat(thrown.getMessage().contains("Invalid mail address"), is(true));
  }
}
