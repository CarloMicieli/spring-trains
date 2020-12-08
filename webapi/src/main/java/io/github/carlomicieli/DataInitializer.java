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
package io.github.carlomicieli;

import io.github.carlomicieli.brands.BrandKind;
import io.github.carlomicieli.countries.Country;
import io.github.carlomicieli.persistence.catalog.brands.JpaBrand;
import io.github.carlomicieli.persistence.catalog.brands.JpaBrandRepository;
import io.github.carlomicieli.persistence.catalog.railways.JpaRailway;
import io.github.carlomicieli.persistence.catalog.railways.JpaRailwayRepository;
import io.github.carlomicieli.persistence.catalog.scales.JpaScale;
import io.github.carlomicieli.persistence.catalog.scales.JpaScaleGauge;
import io.github.carlomicieli.persistence.catalog.scales.JpaScaleRepository;
import io.github.carlomicieli.scales.Ratio;
import io.github.carlomicieli.util.Slug;
import io.github.carlomicieli.valueobject.TrackGauge;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
@Log4j2
public class DataInitializer implements CommandLineRunner {
  @Autowired JpaBrandRepository brands;
  @Autowired JpaRailwayRepository railways;
  @Autowired JpaScaleRepository scales;

  @Override
  public void run(String... args) throws Exception {
    log.info("RUNNING init....");

    brands.deleteAll();
    railways.deleteAll();
    scales.deleteAll();

    var newBrand =
        JpaBrand.builder()
            .name("ACME")
            .slug(Slug.of("ACME"))
            .brandKind(BrandKind.INDUSTRIAL)
            .createdDate(Instant.now())
            .build();
    brands.save(newBrand);

    var brandsList = brands.findAll();
    log.info("{} brand(s) found", brandsList.size());

    brandsList.forEach(
        b -> {
          log.info("ID={}", b.getId());
          log.info("NAME={}", b.getName());
        });

    var newRailway =
        JpaRailway.builder()
            .name("FS")
            .slug(Slug.of("FS"))
            .country(Country.of("IT"))
            .createdDate(Instant.now())
            .build();
    railways.save(newRailway);

    var railwaysList = railways.findAll();
    log.info("{} railway(s) found", railwaysList.size());

    railwaysList.forEach(
        b -> {
          log.info("ID={}", b.getId());
          log.info("NAME={}", b.getName());
        });

    var newScale =
        JpaScale.builder()
            .name("FS")
            .slug(Slug.of("FS"))
            .gauge(JpaScaleGauge.builder().trackGauge(TrackGauge.STANDARD).build())
            .ratio(Ratio.of(BigDecimal.valueOf(87)))
            .createdDate(Instant.now())
            .build();
    scales.save(newScale);

    var scalesList = scales.findAll();
    log.info("{} scale(s) found", scalesList.size());

    scalesList.forEach(
        b -> {
          log.info("ID={}", b.getId());
          log.info("NAME={}", b.getName());
          log.info("RATIO={}", b.getRatio());
          log.info("GAUGE={}", b.getGauge());
        });
  }
}
