package org.edudev.domain.products;

import dev.morphia.annotations.*;
import org.edudev.arch.domain.DomainEntity;

import java.math.BigDecimal;
import java.util.UUID;

import static dev.morphia.utils.IndexType.TEXT;
import static java.math.BigDecimal.ZERO;

@Indexes(@Index(fields = @Field(value = "$**", type = TEXT)))
@Entity(value = "products", useDiscriminator = false)
public final class Product implements DomainEntity {

    @Id
    private final String id = UUID.randomUUID().toString();

    private String name;

    private String description;

    private BigDecimal price;

    public Product() {
        this.name = "";
        this.description = "";
        this.price = ZERO;
    }

    public Product(final String name, final String description, final BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public String getId() { return this.id; }

    public String getName() { return name; }

    public void setName(final String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(final String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }

    public void setPrice(final BigDecimal price) { this.price = price; }


}
