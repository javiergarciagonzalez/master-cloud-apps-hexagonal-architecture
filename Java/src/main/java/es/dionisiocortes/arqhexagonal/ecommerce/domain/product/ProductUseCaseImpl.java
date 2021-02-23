package es.dionisiocortes.arqhexagonal.ecommerce.domain.product;

import java.util.Collection;
import java.util.Optional;

public class ProductUseCaseImpl implements ProductUseCase {

    private ProductRepository productRepository;

    public ProductUseCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public FullProductDto createProduct(ProductDto productDto) {
        FullProductDto product = FullProductDto.fromProductDto(productDto);
        FullProductDto fullProductDto = productRepository.save(product);
        return fullProductDto;
    }

    @Override
    public Collection<FullProductDto> findAllProducts() {
        return productRepository.findAllProducts();
    }

    @Override
    public Optional<FullProductDto> findProductById(Long id) {
        return productRepository.findProductById(id);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
