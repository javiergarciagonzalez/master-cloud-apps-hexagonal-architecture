package es.dionisiocortes.arqhexagonal.ecommerce.infrastructure.product.model;

import es.dionisiocortes.arqhexagonal.ecommerce.domain.product.FullProductDto;
import es.dionisiocortes.arqhexagonal.ecommerce.infrastructure.shoppingcart.model.CartItemEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String category;
    private String manufacturer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productEntity")
    private Collection<CartItemEntity> cartItemEntities = Collections.emptyList();


    public ProductEntity() {
    }


    public ProductEntity(String name, String description, String category, String manufacturer) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.manufacturer = manufacturer;
    }

    public ProductEntity(Long id, String name, String description, String category, String manufacturer) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.manufacturer = manufacturer;
    }

    public static ProductEntity fromFullProductDto(FullProductDto FullProductDto) {
        return new ProductEntity(
                FullProductDto.getId(),
                FullProductDto.getName(),
                FullProductDto.getDescription(),
                FullProductDto.getCategory(),
                FullProductDto.getManufacturer());
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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
