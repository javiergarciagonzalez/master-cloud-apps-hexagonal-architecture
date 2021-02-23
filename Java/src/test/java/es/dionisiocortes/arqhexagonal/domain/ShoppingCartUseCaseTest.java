package es.dionisiocortes.arqhexagonal.domain;

import es.dionisiocortes.arqhexagonal.ecommerce.domain.cartitem.CartItemDto;
import es.dionisiocortes.arqhexagonal.ecommerce.domain.product.FullProductDto;
import es.dionisiocortes.arqhexagonal.ecommerce.domain.shoppingcart.FullShoppingCartDto;
import es.dionisiocortes.arqhexagonal.ecommerce.domain.shoppingcart.ShoppingCartRepository;
import es.dionisiocortes.arqhexagonal.ecommerce.domain.shoppingcart.ShoppingCartUseCaseImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ShoppingCartUseCase unit tests")
public class ShoppingCartUseCaseTest {

    @Mock
    ShoppingCartRepository shoppingCartRepository;

    @InjectMocks
    ShoppingCartUseCaseImpl shoppingCartUseCaseImpl;

    @Test
    @DisplayName("Given a cart when creating it is ok")
    void givenACartWhenIsCreatedThenOK() {

        FullShoppingCartDto fullShoppingCartDto = new FullShoppingCartDto();
        fullShoppingCartDto.setId(1L);
        when(shoppingCartRepository.create()).thenReturn(fullShoppingCartDto);

        FullShoppingCartDto savedFullShoppingCartDto = shoppingCartUseCaseImpl.createShoppingCart();

        verify(shoppingCartRepository, times(1)).create();
        assertEquals(1L, savedFullShoppingCartDto.getId());
        assertEquals(fullShoppingCartDto.getItems(), savedFullShoppingCartDto.getItems());
        assertEquals(fullShoppingCartDto.isFinished(), savedFullShoppingCartDto.isFinished());

    }

    @Test
    @DisplayName("Given a cart when adding a product then is ok")
    void givenACartWhenAddAProductThenIsOk() {

        FullShoppingCartDto fullShoppingCartDto = new FullShoppingCartDto();

        FullProductDto fullProductDtoReturned = new FullProductDto(1L, "Product01", "Description01", "Category01", "Manufacturer01");

        fullShoppingCartDto.setItems(List.of(new CartItemDto(fullProductDtoReturned, 1)));
        fullShoppingCartDto.setId(1L);

        when(this.shoppingCartRepository.addProduct(1L, 1L, 1)).thenReturn(fullShoppingCartDto);

        FullShoppingCartDto fullShoppingCartDtoWitProduct = shoppingCartUseCaseImpl.addProduct(1L, 1L, 1);

        verify(shoppingCartRepository, times(1)).addProduct(1L, 1L, 1);
        assertEquals(fullShoppingCartDto.getId(), fullShoppingCartDtoWitProduct.getId());
        assertEquals(fullShoppingCartDto.getItems(), fullShoppingCartDtoWitProduct.getItems());
        assertEquals(fullShoppingCartDto.isFinished(), fullShoppingCartDtoWitProduct.isFinished());


    }


}
