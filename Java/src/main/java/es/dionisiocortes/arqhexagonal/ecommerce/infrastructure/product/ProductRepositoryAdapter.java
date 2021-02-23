package es.dionisiocortes.arqhexagonal.ecommerce.infrastructure.product;

import es.dionisiocortes.arqhexagonal.ecommerce.domain.product.FullProductDto;
import es.dionisiocortes.arqhexagonal.ecommerce.domain.product.ProductRepository;
import es.dionisiocortes.arqhexagonal.ecommerce.infrastructure.product.model.ProductEntity;
import es.dionisiocortes.arqhexagonal.ecommerce.infrastructure.product.repository.ProductJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductRepositoryAdapter implements ProductRepository {

    private ProductJpaRepository productJpaRepository;

    public ProductRepositoryAdapter(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }


    private static FullProductDto toFullBookDto(ProductEntity productEntity) {

        return new FullProductDto(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getCategory(),
                productEntity.getManufacturer());
    }

    @Override
    public FullProductDto save(FullProductDto product) {
        ProductEntity productEntity = new ProductEntity(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getManufacturer());
        ProductEntity savedProductEntity = productJpaRepository.save(productEntity);
        return toFullBookDto(savedProductEntity);
    }

    @Override
    public List<FullProductDto> findAllProducts() {
        List<ProductEntity> products = productJpaRepository.findAll();
        return products.stream().map(ProductRepositoryAdapter::toFullBookDto).collect(Collectors.toList());
    }

    @Override
    public Optional<FullProductDto> findProductById(Long id) {
        Optional<ProductEntity> maybeAProduct = productJpaRepository.findById(id);
        return maybeAProduct.map(ProductRepositoryAdapter::toFullBookDto);
    }

    @Override
    public void deleteById(Long id) {
        this.productJpaRepository.deleteById(id);
    }
}
