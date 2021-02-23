package es.dionisiocortes.arqhexagonal.ecommerce.domain.shoppingcart;

import java.util.Optional;

public interface ShoppingCartUseCase {

    FullShoppingCartDto createShoppingCart();

    Optional<FullShoppingCartDto> findShoppingCartById(long id);

    boolean deleteById(long id);

    FullShoppingCartDto finishShoppingCart(long id) throws ShoppingCartValidationException, ShoppingCartNotFoundException;

    FullShoppingCartDto addProduct(long id, long productId, int prodQuantity);

    Optional<FullShoppingCartDto> deleteProduct(long id, long productId);

}
