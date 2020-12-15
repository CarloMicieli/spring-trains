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
package io.github.carlomicieli.scales.usecases.createscale;

import io.github.carlomicieli.usecases.boundaries.output.UseCaseOutput;
import io.github.carlomicieli.util.Slug;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(staticName = "of")
public class CreateScaleOutput implements UseCaseOutput {
  Slug slug;
}
