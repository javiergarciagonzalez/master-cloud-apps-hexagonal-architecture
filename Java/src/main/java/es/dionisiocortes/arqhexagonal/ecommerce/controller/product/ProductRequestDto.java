package es.dionisiocortes.arqhexagonal.ecommerce.controller.product;

import es.dionisiocortes.arqhexagonal.ecommerce.domain.product.FullProductDto;

public class ProductRequestDto {

    private String name;
    private String description;
    private String category;
    private String manufacturer;

    public ProductRequestDto() {
    }

    public ProductRequestDto(String name, String description, String category, String manufacturer) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.manufacturer = manufacturer;
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
