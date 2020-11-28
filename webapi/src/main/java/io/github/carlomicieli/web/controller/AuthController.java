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
package io.github.carlomicieli.web.controller;

import io.github.carlomicieli.security.SqlUserPrincipal;
import io.github.carlomicieli.web.request.LoginRequest;
import io.github.carlomicieli.web.request.RegisterRequest;
import io.github.carlomicieli.web.security.JwtTokenUtil;
import io.github.carlomicieli.web.security.UserService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Log4j2
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenUtil jwtTokenUtil;
  private final UserService userService;

  @PostMapping("/signin")
  public ResponseEntity<Void> login(@Valid @RequestBody LoginRequest login) {
    log.debug("POST login");
    try {
      Authentication authenticate =
          authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(login.getUserName(), login.getPassword()));

      SqlUserPrincipal user = (SqlUserPrincipal) authenticate.getPrincipal();

      return ResponseEntity.noContent()
          .header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(user))
          .build();
    } catch (BadCredentialsException ex) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }

  @PostMapping("/register")
  public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest register) {
    log.debug("POST user {} just register", register.getUserName());

    userService.createUser(register);

    return ResponseEntity.ok().build();
  }
}
