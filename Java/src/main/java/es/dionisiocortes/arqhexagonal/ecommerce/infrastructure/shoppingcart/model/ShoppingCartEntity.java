package es.dionisiocortes.arqhexagonal.ecommerce.infrastructure.shoppingcart.model;

import es.dionisiocortes.arqhexagonal.ecommerce.domain.cartitem.CartItemDto;
import es.dionisiocortes.arqhexagonal.ecommerce.domain.shoppingcart.FullShoppingCartDto;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class ShoppingCartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shoppingCartEntity", cascade = CascadeType.ALL)
    private List<CartItemEntity> items = Collections.emptyList();

    private boolean finished;

    public ShoppingCartEntity() {
    }

    public ShoppingCartEntity(Long id, List<CartItemEntity> items, boolean finished) {
        Id = id;
        this.items = items;
        this.finished = finished;
    }

    public static FullShoppingCartDto toFullShoppingCartDto(ShoppingCartEntity shoppingCartEntity) {

        List<CartItemDto> items = shoppingCartEntity.getItems().stream().map(CartItemEntity::toCartItemDto).collect(Collectors.toList());

        return new FullShoppingCartDto(
                shoppingCartEntity.getId(),
                items,
                shoppingCartEntity.isFinished());
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public List<CartItemEntity> getItems() {
        return items;
    }

    public void setItems(List<CartItemEntity> items) {
        this.items = items;
    }

    public void addItem(CartItemEntity item) {
        this.items.add(item);
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
