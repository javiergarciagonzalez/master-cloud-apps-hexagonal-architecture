package es.dionisiocortes.arqhexagonal.ecommerce.domain.product;

public class Product {

    private String name;
    private String description;
    private String category;
    private String manufacturer;

    public Product(String name, String description, String category, String manufacturer) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.manufacturer = manufacturer;
    }

    public Product() {
    }
}
