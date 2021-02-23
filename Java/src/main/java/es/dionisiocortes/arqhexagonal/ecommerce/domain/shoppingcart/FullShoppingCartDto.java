package es.dionisiocortes.arqhexagonal.ecommerce.domain.shoppingcart;

import es.dionisiocortes.arqhexagonal.ecommerce.domain.cartitem.CartItemDto;

import java.util.List;

public class FullShoppingCartDto {

    private Long Id;
    private List<CartItemDto> items;
    private boolean finished;

    public FullShoppingCartDto() {
    }

    public FullShoppingCartDto(List<CartItemDto> items, boolean finished) {
        this.items = items;
        this.finished = finished;
    }

    public FullShoppingCartDto(Long id, List<CartItemDto> items, boolean finished) {
        Id = id;
        this.items = items;
        this.finished = finished;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public List<CartItemDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
