package es.dionisiocortes.arqhexagonal.ecommerce.domain.product;

import java.util.Collection;
import java.util.Optional;

public interface ProductUseCase {

    FullProductDto createProduct(ProductDto product);

    Collection<FullProductDto> findAllProducts();

    Optional<FullProductDto> findProductById(Long id);

    void deleteById(Long id);
}
