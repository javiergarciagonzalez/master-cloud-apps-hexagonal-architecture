package es.dionisiocortes.arqhexagonal.ecommerce.infrastructure.shoppingcart.model;

import es.dionisiocortes.arqhexagonal.ecommerce.domain.cartitem.CartItemDto;
import es.dionisiocortes.arqhexagonal.ecommerce.domain.product.FullProductDto;
import es.dionisiocortes.arqhexagonal.ecommerce.infrastructure.product.model.ProductEntity;

import javax.persistence.*;

@Entity
public class CartItemEntity {

    int productNumber;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private ShoppingCartEntity shoppingCartEntity;
    @ManyToOne
    private ProductEntity productEntity;

    public CartItemEntity(Long id, ShoppingCartEntity shoppingCartEntity, ProductEntity productEntity, int productNumber) {
        this.id = id;
        this.shoppingCartEntity = shoppingCartEntity;
        this.productEntity = productEntity;
        this.productNumber = productNumber;
    }

    public CartItemEntity(ShoppingCartEntity shoppingCartEntity, ProductEntity productEntity, int productNumber) {
        this.shoppingCartEntity = shoppingCartEntity;
        this.productEntity = productEntity;
        this.productNumber = productNumber;
    }

    public CartItemEntity(ProductEntity productEntity, int productNumber) {
        this.productEntity = productEntity;
        this.productNumber = productNumber;
    }

    public CartItemEntity() {
    }

    public static CartItemDto toCartItemDto(CartItemEntity cartItemEntity) {
        return new CartItemDto(
                FullProductDto.fromProductEntity(cartItemEntity.getProductEntity()),
                cartItemEntity.getProductNumber());
    }

    public static CartItemEntity fromCartItemDto(CartItemDto cartItemDto) {
        return new CartItemEntity(ProductEntity.fromFullProductDto(cartItemDto.getProduct()),
                cartItemDto.getProductNumber());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShoppingCartEntity getShoppingCartEntity() {
        return shoppingCartEntity;
    }

    public void setShoppingCartEntity(ShoppingCartEntity shoppingCartEntity) {
        this.shoppingCartEntity = shoppingCartEntity;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }
}
