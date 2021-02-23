package es.dionisiocortes.arqhexagonal.ecommerce.domain.shoppingcart;

import es.dionisiocortes.arqhexagonal.ecommerce.service.ShoppingCartValidationService;

import java.util.Optional;

public class ShoppingCartUseCaseImpl implements ShoppingCartUseCase {

    private ShoppingCartRepository shoppingCartRepository;
    private ShoppingCartValidationService shoppingCartValidationService;

    public ShoppingCartUseCaseImpl(ShoppingCartRepository shoppingCartRepository, ShoppingCartValidationService shoppingCartValidationService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.shoppingCartValidationService = shoppingCartValidationService;
    }

    @Override
    public FullShoppingCartDto createShoppingCart() {
        return shoppingCartRepository.create();
    }

    @Override
    public Optional<FullShoppingCartDto> findShoppingCartById(long id) {
        return shoppingCartRepository.findById(id);
    }

    @Override
    public boolean deleteById(long id) {
        boolean exist = shoppingCartRepository.existShoppingCartById(id);
        if (exist) {
            shoppingCartRepository.deleteById(id);
        }
        return exist;
    }

    @Override
    public FullShoppingCartDto finishShoppingCart(long id) throws ShoppingCartValidationException, ShoppingCartNotFoundException {
        FullShoppingCartDto fullShoppingCartDto = this.findShoppingCartById(id).orElseThrow(ShoppingCartNotFoundException::new);
        boolean isValid = shoppingCartValidationService.validate(fullShoppingCartDto);
        if (isValid) {
            fullShoppingCartDto.setFinished(true);
            shoppingCartRepository.finishShoppingCartById(id);
        } else {
            throw new ShoppingCartValidationException();
        }

        return fullShoppingCartDto;
    }

    @Override
    public FullShoppingCartDto addProduct(long id, long productId, int prodQuantity) {
        return shoppingCartRepository.addProduct(id, productId, prodQuantity);

    }

    @Override
    public Optional<FullShoppingCartDto> deleteProduct(long id, long productId) {
        return shoppingCartRepository.deleteProduct(id, productId);
    }
}
