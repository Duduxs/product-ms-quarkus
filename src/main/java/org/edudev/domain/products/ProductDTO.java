package org.edudev.domain.products;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static java.math.BigDecimal.ZERO;

public class ProductDTO {

    private final String id;

    private final String name;

    private final String description;

    private final BigDecimal price;

    ProductDTO(){
        this.id = UUID.randomUUID().toString();
        this.name = "";
        this.description = "";
        this.price = ZERO;
    }

    public ProductDTO(final Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }

    public String getId() { return id; }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public BigDecimal getPrice() { return price; }

    public Product update(final Optional<Product> optionalProduct) {
        if (optionalProduct.isPresent()) {
            final Product product = optionalProduct.get();
            product.setName(this.name);
            product.setDescription(this.description);
            product.setPrice(this.price);
            return product;
        }

        return new Product(this.name, this.description, this.price);
    }

}
