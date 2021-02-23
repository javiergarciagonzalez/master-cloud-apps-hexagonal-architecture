package es.dionisiocortes.arqhexagonal.ecommerce.domain.cartitem;

import es.dionisiocortes.arqhexagonal.ecommerce.domain.product.Product;

public class CartItem {

    Product product;
    int productNumber;

    public CartItem() {
    }

    public CartItem(Product product, int productNumber) {
        this.product = product;
        this.productNumber = productNumber;
    }

}
