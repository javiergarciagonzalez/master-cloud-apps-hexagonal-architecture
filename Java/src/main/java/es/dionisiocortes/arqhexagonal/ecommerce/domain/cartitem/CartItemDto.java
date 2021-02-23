package es.dionisiocortes.arqhexagonal.ecommerce.domain.cartitem;

import es.dionisiocortes.arqhexagonal.ecommerce.domain.product.FullProductDto;

public class CartItemDto {

    FullProductDto fullProduct;
    int productNumber;

    public CartItemDto() {
    }

    public CartItemDto(FullProductDto fullProduct, int productNumber) {
        this.fullProduct = fullProduct;
        this.productNumber = productNumber;
    }

    public FullProductDto getProduct() {
        return fullProduct;
    }

    public void setProduct(FullProductDto fullProduct) {
        this.fullProduct = fullProduct;
    }

    public int getProductNumber() {
        return productNumber;
    }

}
