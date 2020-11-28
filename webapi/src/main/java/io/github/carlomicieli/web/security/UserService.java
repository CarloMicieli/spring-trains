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
package io.github.carlomicieli.web.security;

import io.github.carlomicieli.security.User;
import io.github.carlomicieli.security.UserRepository;
import io.github.carlomicieli.web.request.RegisterRequest;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public void createUser(RegisterRequest request) {

    if (userRepository.findByUsername(request.getUserName()).isPresent()) {
      throw new ValidationException("Username already in use");
    }

    var newUser = new User();
    newUser.setUsername(request.getUserName());
    newUser.setPassword(passwordEncoder.encode(request.getPassword()));
    newUser.setEnabled(true);

    userRepository.save(newUser);
  }
}
