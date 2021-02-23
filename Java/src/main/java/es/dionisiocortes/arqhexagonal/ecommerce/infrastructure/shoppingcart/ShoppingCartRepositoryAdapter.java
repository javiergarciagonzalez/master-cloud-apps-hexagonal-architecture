package es.dionisiocortes.arqhexagonal.ecommerce.infrastructure.shoppingcart;

import es.dionisiocortes.arqhexagonal.ecommerce.domain.cartitem.CartItemDto;
import es.dionisiocortes.arqhexagonal.ecommerce.domain.shoppingcart.FullShoppingCartDto;
import es.dionisiocortes.arqhexagonal.ecommerce.domain.shoppingcart.ShoppingCartRepository;
import es.dionisiocortes.arqhexagonal.ecommerce.infrastructure.product.model.ProductEntity;
import es.dionisiocortes.arqhexagonal.ecommerce.infrastructure.product.repository.ProductJpaRepository;
import es.dionisiocortes.arqhexagonal.ecommerce.infrastructure.shoppingcart.model.CartItemEntity;
import es.dionisiocortes.arqhexagonal.ecommerce.infrastructure.shoppingcart.model.ShoppingCartEntity;
import es.dionisiocortes.arqhexagonal.ecommerce.infrastructure.shoppingcart.repository.CartItemJpaRepository;
import es.dionisiocortes.arqhexagonal.ecommerce.infrastructure.shoppingcart.repository.ShoppingCartJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ShoppingCartRepositoryAdapter implements ShoppingCartRepository {

    private ShoppingCartJpaRepository shoppingCartJpaRepositoryRepository;
    private ProductJpaRepository productJpaRepository;
    private CartItemJpaRepository cartItemJpaRepository;

    public ShoppingCartRepositoryAdapter(ShoppingCartJpaRepository shoppingCartJpaRepositoryRepository, ProductJpaRepository productJpaRepository, CartItemJpaRepository cartItemJpaRepository) {
        this.shoppingCartJpaRepositoryRepository = shoppingCartJpaRepositoryRepository;
        this.productJpaRepository = productJpaRepository;
        this.cartItemJpaRepository = cartItemJpaRepository;
    }

    private static FullShoppingCartDto toFullShoppingCartDto(ShoppingCartEntity shoppingCartEntity) {

        List<CartItemDto> items = shoppingCartEntity.getItems().stream().map(CartItemEntity::toCartItemDto).collect(Collectors.toList());

        return new FullShoppingCartDto(
                shoppingCartEntity.getId(),
                items,
                shoppingCartEntity.isFinished());
    }

    @Override
    public FullShoppingCartDto create() {
        ShoppingCartEntity shoppingCartEntity = new ShoppingCartEntity();
        return toFullShoppingCartDto(shoppingCartJpaRepositoryRepository.save(shoppingCartEntity));
    }

    @Override
    public Optional<FullShoppingCartDto> findById(long id) {
        Optional<ShoppingCartEntity> maybeAShoppingCart = shoppingCartJpaRepositoryRepository.findById(id);
        return maybeAShoppingCart.map(ShoppingCartEntity::toFullShoppingCartDto);
    }

    @Override
    public void deleteById(long id) {
        shoppingCartJpaRepositoryRepository.deleteById(id);
    }

    @Override
    public FullShoppingCartDto addProduct(long id, long productId, int prodQuantity) {

        ShoppingCartEntity shoppingCart = shoppingCartJpaRepositoryRepository.findById(id).orElseThrow();
        Optional<CartItemEntity> maybeACartItem = cartItemJpaRepository.findByShoppingCartEntity(shoppingCart);
        ProductEntity productEntity = productJpaRepository.findById(productId).orElseThrow();

        if (maybeACartItem.isPresent()) {
            maybeACartItem.get().setProductNumber(prodQuantity + maybeACartItem.get().getProductNumber());
            productJpaRepository.save(productEntity);
        } else {
            CartItemEntity cartItemEntity = new CartItemEntity(shoppingCart, productEntity, prodQuantity);
            shoppingCart.addItem(cartItemEntity);
            shoppingCartJpaRepositoryRepository.save(shoppingCart);
        }
        return ShoppingCartEntity.toFullShoppingCartDto(shoppingCart);
    }

    @Override
    public Optional<FullShoppingCartDto> deleteProduct(long id, long productId) {
        ShoppingCartEntity shoppingCart = shoppingCartJpaRepositoryRepository.findById(id).orElseThrow();
        ProductEntity productEntity = productJpaRepository.findById(productId).orElseThrow();
        CartItemEntity cartEntity = cartItemJpaRepository.findByShoppingCartEntityAndProductEntity(shoppingCart, productEntity).orElseThrow();
        shoppingCart.getItems().removeIf(item -> productId == item.getProductEntity().getId());
        cartItemJpaRepository.delete(cartEntity);

        return Optional.of(ShoppingCartEntity.toFullShoppingCartDto(shoppingCart));

    }

    @Override
    public void finishShoppingCartById(long id) {
        this.shoppingCartJpaRepositoryRepository.updateShoppingCartFinishedbyId(id);
    }

    @Override
    public boolean existShoppingCartById(long id) {
        return this.shoppingCartJpaRepositoryRepository.existsById(id);
    }

}
