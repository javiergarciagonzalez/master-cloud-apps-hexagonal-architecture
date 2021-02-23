package es.dionisiocortes.arqhexagonal.ecommerce.infrastructure.product.repository;

import es.dionisiocortes.arqhexagonal.ecommerce.infrastructure.product.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {

}
