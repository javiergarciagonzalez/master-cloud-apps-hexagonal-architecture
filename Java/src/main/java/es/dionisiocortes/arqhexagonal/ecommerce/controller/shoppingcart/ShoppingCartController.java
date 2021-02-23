package es.dionisiocortes.arqhexagonal.ecommerce.controller.shoppingcart;

import es.dionisiocortes.arqhexagonal.ecommerce.domain.shoppingcart.FullShoppingCartDto;
import es.dionisiocortes.arqhexagonal.ecommerce.service.ProductService;
import es.dionisiocortes.arqhexagonal.ecommerce.service.ShoppingCartNotFoundExceptionService;
import es.dionisiocortes.arqhexagonal.ecommerce.service.ShoppingCartService;
import es.dionisiocortes.arqhexagonal.ecommerce.service.ShoppingCartValidationExceptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RequestMapping("/api")
@RestController
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;
    private ProductService productService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    @PostMapping("/shoppingcarts")
    public ShoppingCartResponseDto createShoppingCart() {
        ShoppingCartResponseDto shoppingCartResponseDto = this.shoppingCartService.save();
        return shoppingCartResponseDto;
    }

    @PatchMapping("/shoppingcarts/{id}")
    public ShoppingCartResponseDto updateShoppingCart(@PathVariable long id) {
        try {
            return this.shoppingCartService.finishShoppingCart(id);
        } catch (ShoppingCartNotFoundExceptionService e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found", e);
        } catch (ShoppingCartValidationExceptionService e1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cart not validated", e1);
        }
    }

    @GetMapping("/shoppingcarts/{id}")
    public ShoppingCartResponseDto getShoppingCart(@PathVariable long id) {
        Optional<ShoppingCartResponseDto> maybeACart = this.shoppingCartService.findById(id);
        if (maybeACart.isPresent()) {
            return maybeACart.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found");
        }
    }

    @DeleteMapping("/shoppingcarts/{id}")
    public ResponseEntity deleteShoppingCart(@PathVariable long id) {
        if (this.shoppingCartService.deleteById(id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found");
        }
    }

    @PostMapping("/shoppingcarts/{id}/product/{prodId}/quantity/{prodQuantity}")
    public ShoppingCartResponseDto addProductToShoppingCart(@PathVariable long id, @PathVariable long prodId, @PathVariable int prodQuantity) {
        return this.shoppingCartService.addProduct(id, prodId, prodQuantity);
    }

    @DeleteMapping("/shoppingcarts/{id}/product/{prodId}")
    public FullShoppingCartDto deleteProductFromShoppingCart(@PathVariable long id, @PathVariable long prodId) {
        Optional<FullShoppingCartDto> maybeACart = this.shoppingCartService.deleteProduct(id, prodId);
        if (maybeACart.isPresent()) {
            return maybeACart.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found");
        }
    }

}
