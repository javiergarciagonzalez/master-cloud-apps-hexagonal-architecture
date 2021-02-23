package es.dionisiocortes.arqhexagonal;

import es.dionisiocortes.arqhexagonal.ecommerce.domain.product.ProductRepository;
import es.dionisiocortes.arqhexagonal.ecommerce.domain.product.ProductUseCase;
import es.dionisiocortes.arqhexagonal.ecommerce.domain.product.ProductUseCaseImpl;
import es.dionisiocortes.arqhexagonal.ecommerce.domain.shoppingcart.ShoppingCartRepository;
import es.dionisiocortes.arqhexagonal.ecommerce.domain.shoppingcart.ShoppingCartUseCase;
import es.dionisiocortes.arqhexagonal.ecommerce.domain.shoppingcart.ShoppingCartUseCaseImpl;
import es.dionisiocortes.arqhexagonal.ecommerce.service.ShoppingCartValidationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public ProductUseCase productUseCase(ProductRepository productRepository) {
        return new ProductUseCaseImpl(productRepository);
    }

    @Bean
    public ShoppingCartUseCase shoppingCartUseCase(ShoppingCartRepository shoppingCartRepository, ShoppingCartValidationService shoppingCartValidationService) {
        return new ShoppingCartUseCaseImpl(shoppingCartRepository, shoppingCartValidationService);
    }

}
