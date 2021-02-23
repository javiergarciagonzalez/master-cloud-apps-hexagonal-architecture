package es.dionisiocortes.arqhexagonal.ecommerce.domain.product;

import es.dionisiocortes.arqhexagonal.ecommerce.infrastructure.product.model.ProductEntity;

public class FullProductDto {

    private Long id;
    private String name;
    private String description;
    private String category;
    private String manufacturer;

    public FullProductDto() {
    }

    public FullProductDto(String name, String description, String category, String manufacturer) {
        this(null, name, description, category, manufacturer);
    }

    public FullProductDto(Long id, String name, String description, String category, String manufacturer) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.manufacturer = manufacturer;
    }

    public static FullProductDto fromProductDto(ProductDto product) {
        return new FullProductDto(
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getManufacturer());
    }

    public static FullProductDto fromProductEntity(ProductEntity productEntity) {
        return new FullProductDto(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getCategory(),
                productEntity.getManufacturer());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getManufacturer() {
        return manufacturer;
    }

}
