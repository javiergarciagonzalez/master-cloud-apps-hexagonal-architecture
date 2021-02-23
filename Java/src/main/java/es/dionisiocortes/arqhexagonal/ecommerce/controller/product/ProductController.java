package es.dionisiocortes.arqhexagonal.ecommerce.controller.product;

import es.dionisiocortes.arqhexagonal.ecommerce.domain.product.FullProductDto;
import es.dionisiocortes.arqhexagonal.ecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RequestMapping("/api")
@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/")
    public Collection<ProductResponseDto> getProducts() {
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public ProductResponseDto getProduct(@PathVariable long id) {
        Optional<ProductResponseDto> product = productService.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteById(id);
    }

    @PostMapping("/products/")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto product) {

        FullProductDto fullProductDto = productService.save(product);

        ProductResponseDto responseBookDto = new ProductResponseDto(
                fullProductDto.getId(),
                fullProductDto.getName(),
                fullProductDto.getDescription(),
                fullProductDto.getCategory(),
                fullProductDto.getManufacturer());

        URI location = fromCurrentRequest().path("/{id}")
                .buildAndExpand(fullProductDto.getId()).toUri();

        return ResponseEntity.created(location).body(responseBookDto);
    }

}
