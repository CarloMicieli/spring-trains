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
import io.github.carlomicieli.catalogitems.Control;
import io.github.carlomicieli.catalogitems.Epoch;
import io.github.carlomicieli.catalogitems.ItemNumber;
import io.github.carlomicieli.catalogitems.PowerMethod;
import io.github.carlomicieli.catalogitems.deliverydates.DeliveryDate;
import io.github.carlomicieli.catalogitems.rollingstocks.*;
import io.github.carlomicieli.countries.Country;
import io.github.carlomicieli.persistence.catalog.brands.JpaBrand;
import io.github.carlomicieli.persistence.catalog.brands.JpaBrandRepository;
import io.github.carlomicieli.persistence.catalog.catalogitems.JpaCatalogItem;
import io.github.carlomicieli.persistence.catalog.catalogitems.JpaCatalogItemRepository;
import io.github.carlomicieli.persistence.catalog.catalogitems.JpaRollingStock;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Profile("dev")
@Log4j2
public class DataInitializer implements CommandLineRunner {
  @Autowired JpaBrandRepository brands;
  @Autowired JpaRailwayRepository railways;
  @Autowired JpaScaleRepository scales;
  @Autowired
  JpaCatalogItemRepository catalogItems;

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    log.info("RUNNING init....");

    catalogItems.deleteAll();
    brands.deleteAll();
    railways.deleteAll();
    scales.deleteAll();

    UUID brandId = initBrands();
    UUID railwayId = initRailways();
    UUID scaleId = initScales();

    var brand = brands.findById(brandId).get();
    var railway = railways.findById(railwayId).get();
    var scale = scales.findById(scaleId).get();

    var rollingStocks = Collections.singleton(
            JpaRollingStock.builder()
                    .category(Category.FREIGHT_CAR)
                    .control(Control.DCC)
                    .couplers(Couplers.NEM_352)
                    .depot("Milano Centrale")
                    .lengthInches(BigDecimal.valueOf(210))
                    // .epoch(Epoch.II)
                    .minRadius(MinRadius.ofMillimeters(360))
                    .roadNumber("123456")
                    .series("I series")
                    .subCategory(FreightCarType.COVERED_FREIGHT_CARS.toString())
                    .serviceLevel(ServiceLevel.FirstClass)
                    .railway(railway)
                    .livery("Blue")
                    .typeName("Type name")
                    .build()

    );

    var item = JpaCatalogItem.builder()
            .brand(brand)
            .itemNumber(ItemNumber.of("123456"))
            .description("My description goes here")
            .modifiedDate(Instant.now())
            .available(true)
            .createdDate(Instant.now())
            .deliveryDate(DeliveryDate.of(2020))
            .modelDescription("Model description goes here")
            .prototypeDescription("Prototype description goes here")
            .powerMethod(PowerMethod.AC)
            .slug(Slug.ofValues("ACME", "123456"))
            .scale(scale)
            .version(42)
            .rollingStocks(rollingStocks)
            .build();

    var catalogItem = catalogItems.saveAndFlush(item);

    var savedItem = catalogItems.findById(catalogItem.getId());
    System.out.println(savedItem.toString());


  }

  private UUID initBrands() {
    var newBrand =
            JpaBrand.builder()
                    .name("ACME")
                    .slug(Slug.of("ACME"))
                    .brandKind(BrandKind.INDUSTRIAL)
                    .createdDate(Instant.now())
                    .build();
    var saved = brands.saveAndFlush(newBrand);

    var brandsList = brands.findAll();
    log.info("{} brand(s) found", brandsList.size());

    brandsList.forEach(
            b -> {
              log.info("ID={}", b.getId());
              log.info("NAME={}", b.getName());
            });

    return saved.getId();
  }

  private UUID initRailways() {
    var newRailway =
            JpaRailway.builder()
                    .name("FS")
                    .slug(Slug.of("FS"))
                    .country(Country.of("IT"))
                    .createdDate(Instant.now())
                    .build();
    var saved = railways.saveAndFlush(newRailway);

    var railwaysList = railways.findAll();
    log.info("{} railway(s) found", railwaysList.size());

    railwaysList.forEach(
            b -> {
              log.info("ID={}", b.getId());
              log.info("NAME={}", b.getName());
            });

    return saved.getId();
  }

  private UUID initScales() {
    var newScale =
            JpaScale.builder()
                    .name("FS")
                    .slug(Slug.of("FS"))
                    .gauge(JpaScaleGauge.builder().trackGauge(TrackGauge.STANDARD).build())
                    .ratio(Ratio.of(BigDecimal.valueOf(87)))
                    .createdDate(Instant.now())
                    .build();
    var saved = scales.saveAndFlush(newScale);

    var scalesList = scales.findAll();
    log.info("{} scale(s) found", scalesList.size());

    scalesList.forEach(
            b -> {
              log.info("ID={}", b.getId());
              log.info("NAME={}", b.getName());
              log.info("RATIO={}", b.getRatio());
              log.info("GAUGE={}", b.getGauge());
            });
    return saved.getId();
  }
}
