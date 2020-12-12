package io.github.carlomicieli.web.catalog.catalogItems.createNew;

import io.github.carlomicieli.persistence.catalog.catalogitems.JpaCatalogItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CreateNewCatalogItemController {
  private final JpaCatalogItemRepository repo;

  @PostMapping("api/catalog-items")
  public ResponseEntity<String> postNew() {
    return ResponseEntity.noContent().build();
  }
}
