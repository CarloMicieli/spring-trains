package io.github.carlomicieli.web.catalog.catalogItems.getById;

import io.github.carlomicieli.persistence.catalog.catalogitems.JpaCatalogItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class GetCatalogItemByIdController {

  private final JpaCatalogItemRepository repo;


  @GetMapping("api/catalog-items")
  public ResponseEntity<String> get() {





    var results = repo.findAll();

    return ResponseEntity.noContent().build();
  }

}
