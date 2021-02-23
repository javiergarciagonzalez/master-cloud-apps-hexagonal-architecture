package es.dionisiocortes.arqhexagonal.ecommerce.controller.shoppingcart;

import es.dionisiocortes.arqhexagonal.ecommerce.domain.shoppingcart.FullShoppingCartDto;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartResponseDto {

    private Long Id;
    private List<CartItemResponseDto> items;
    private boolean finished;

    public ShoppingCartResponseDto(Long id, List<CartItemResponseDto> items, boolean finished) {
        Id = id;
        this.items = items;
        this.finished = finished;
    }

    public ShoppingCartResponseDto() {
    }

    public static ShoppingCartResponseDto fromFullShoppingCartDto(FullShoppingCartDto fullShoppingCartDto) {

        List<CartItemResponseDto> items = fullShoppingCartDto.getItems().stream().map(CartItemResponseDto::fromCartItemDto).collect(Collectors.toList());

        return new ShoppingCartResponseDto(
                fullShoppingCartDto.getId(),
                items,
                fullShoppingCartDto.isFinished());
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public List<CartItemResponseDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemResponseDto> items) {
        this.items = items;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
