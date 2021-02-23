package es.dionisiocortes.arqhexagonal.ecommerce.infrastructure.shoppingcart.repository;

import es.dionisiocortes.arqhexagonal.ecommerce.infrastructure.product.model.ProductEntity;
import es.dionisiocortes.arqhexagonal.ecommerce.infrastructure.shoppingcart.model.CartItemEntity;
import es.dionisiocortes.arqhexagonal.ecommerce.infrastructure.shoppingcart.model.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemJpaRepository extends JpaRepository<CartItemEntity, Long> {

    Optional<CartItemEntity> findByShoppingCartEntity(ShoppingCartEntity shoppingCartEntity);

    Optional<CartItemEntity> findByShoppingCartEntityAndProductEntity(ShoppingCartEntity shoppingCartEntity, ProductEntity productEntity);


}
