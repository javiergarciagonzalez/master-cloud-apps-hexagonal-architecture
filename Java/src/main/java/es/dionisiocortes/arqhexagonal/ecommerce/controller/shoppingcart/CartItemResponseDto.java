package es.dionisiocortes.arqhexagonal.ecommerce.controller.shoppingcart;

import es.dionisiocortes.arqhexagonal.ecommerce.domain.cartitem.CartItemDto;
import es.dionisiocortes.arqhexagonal.ecommerce.domain.product.FullProductDto;

public class CartItemResponseDto {

    FullProductDto fullProduct;
    int productNumber;

    public CartItemResponseDto() {
    }

    public CartItemResponseDto(FullProductDto fullProduct, int productNumber) {
        this.fullProduct = fullProduct;
        this.productNumber = productNumber;
    }

    public FullProductDto getProductId() {
        return fullProduct;
    }

    public void setProductId(FullProductDto fullProduct) {
        this.fullProduct = fullProduct;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public static CartItemResponseDto fromCartItemDto(CartItemDto cartItemDto) {
        return new CartItemResponseDto(
                cartItemDto.getProduct(),
                cartItemDto.getProductNumber());
    }
}
