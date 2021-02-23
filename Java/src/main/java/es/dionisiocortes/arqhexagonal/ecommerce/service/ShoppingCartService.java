package es.dionisiocortes.arqhexagonal.ecommerce.service;

import es.dionisiocortes.arqhexagonal.ecommerce.controller.shoppingcart.ShoppingCartResponseDto;
import es.dionisiocortes.arqhexagonal.ecommerce.domain.shoppingcart.FullShoppingCartDto;
import es.dionisiocortes.arqhexagonal.ecommerce.domain.shoppingcart.ShoppingCartNotFoundException;
import es.dionisiocortes.arqhexagonal.ecommerce.domain.shoppingcart.ShoppingCartUseCase;
import es.dionisiocortes.arqhexagonal.ecommerce.domain.shoppingcart.ShoppingCartValidationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartService {
    private ShoppingCartUseCase shoppingCartUseCase;

    public ShoppingCartService(ShoppingCartUseCase shoppingCartUseCase) {
        this.shoppingCartUseCase = shoppingCartUseCase;
    }

    public Optional<ShoppingCartResponseDto> findById(Long id) {
        return shoppingCartUseCase.findShoppingCartById(id).map(ShoppingCartResponseDto::fromFullShoppingCartDto);
    }

    public ShoppingCartResponseDto save() {
        FullShoppingCartDto fullShoppingCartDto = shoppingCartUseCase.createShoppingCart();
        ShoppingCartResponseDto shoppingCartResponseDto = ShoppingCartResponseDto.fromFullShoppingCartDto(fullShoppingCartDto);
        return shoppingCartResponseDto;
    }

    public boolean deleteById(Long id) {
        return shoppingCartUseCase.deleteById(id);
    }

    public ShoppingCartResponseDto addProduct(long id, long productId, int prodQuantity) {
        FullShoppingCartDto fullShoppingCartDto = shoppingCartUseCase.addProduct(id, productId, prodQuantity);
        ShoppingCartResponseDto shoppingCartResponseDto = ShoppingCartResponseDto.fromFullShoppingCartDto(fullShoppingCartDto);
        return shoppingCartResponseDto;
    }

    public ShoppingCartResponseDto finishShoppingCart(long id) throws ShoppingCartValidationExceptionService, ShoppingCartNotFoundExceptionService {
        try {
            FullShoppingCartDto fullShoppingCartDto = this.shoppingCartUseCase.finishShoppingCart(id);
            return ShoppingCartResponseDto.fromFullShoppingCartDto(fullShoppingCartDto);
        } catch (ShoppingCartValidationException e) {
            throw new ShoppingCartValidationExceptionService();
        } catch (ShoppingCartNotFoundException e1) {
            throw new ShoppingCartNotFoundExceptionService();
        }
    }

    public Optional<FullShoppingCartDto> deleteProduct(Long id, long productId) {
        return shoppingCartUseCase.deleteProduct(id, productId);
    }

}
