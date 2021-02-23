package es.dionisiocortes.arqhexagonal.ecommerce.service;

import es.dionisiocortes.arqhexagonal.ecommerce.domain.shoppingcart.FullShoppingCartDto;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ShoppingCartValidationService {

    public boolean validate(FullShoppingCartDto fullShoppingCartDto) {
        return new Random().nextBoolean();
    }

}
