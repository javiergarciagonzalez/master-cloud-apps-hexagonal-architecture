package es.dionisiocortes.arqhexagonal.domain;

import es.dionisiocortes.arqhexagonal.ecommerce.domain.product.FullProductDto;
import es.dionisiocortes.arqhexagonal.ecommerce.domain.product.ProductDto;
import es.dionisiocortes.arqhexagonal.ecommerce.domain.product.ProductRepository;
import es.dionisiocortes.arqhexagonal.ecommerce.domain.product.ProductUseCaseImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProductUseCaseTest unit tests")
public class ProductUseCaseTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductUseCaseImpl productUseCaseImpl;

    @Test
    @DisplayName("Given a product when adding it to the database then the product is inserted correctly")
    void givenAProductWhenInsertItThenOK() {

        ProductDto productDto = new ProductDto("Product01", "Description01", "Category01", "Manufacturer01");
        FullProductDto fullProductDtoReturned = new FullProductDto(1L, "Product01", "Description01", "Category01", "Manufacturer01");

        when(this.productRepository.save(Mockito.any())).thenReturn(fullProductDtoReturned);
        FullProductDto result = this.productUseCaseImpl.createProduct(productDto);

        verify(productRepository, times(1)).save(Mockito.any());
        assertEquals(1L, result.getId());
        assertEquals(productDto.getDescription(), result.getDescription());
        assertEquals(productDto.getName(), result.getName());

    }

    @Test
    @DisplayName("Given an id when deleting it from the database then the product is not there")
    void givenAProductWhenDeleteItThenOK() {
        Long id = 1L;
        doNothing().when(this.productRepository).deleteById(id);
        this.productUseCaseImpl.deleteById(id);
        verify(this.productRepository, times(1)).deleteById(id);

    }

}
