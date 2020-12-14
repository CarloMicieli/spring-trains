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
package io.github.carlomicieli.persistence.images;

import java.time.Instant;
import java.util.UUID;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "images")
@Builder
@Data
@With
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class JpaImage {
  @Id
  @Column(name = "image_id")
  UUID id;

  @Column(name = "content_type")
  String contentType;

  @Lob
  @Column(name = "content", columnDefinition = "bytea")
  byte[] content;

  @Column(name = "is_deleted")
  boolean isDeleted;

  @Column(name = "created")
  Instant createdDate;
}
