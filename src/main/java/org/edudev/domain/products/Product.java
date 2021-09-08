package org.edudev.domain.products;

import dev.morphia.annotations.*;
import org.edudev.arch.domain.DomainEntity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

import static dev.morphia.utils.IndexType.TEXT;
import static java.math.BigDecimal.ZERO;

@Indexes(@Index(fields = @Field(value = "$**", type = TEXT)))
@Entity(value = "products", useDiscriminator = false)
public final class Product implements DomainEntity {

    @Id
    private final String id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @Min(value = 1, message = "Preço deve ser positivo")
    private BigDecimal price;

    Product() {
        this(UUID.randomUUID().toString());
        this.name = "";
        this.description = "";
        this.price = ZERO;
    }

    public Product(final String name, final String description, final BigDecimal price) {
        this(UUID.randomUUID().toString());
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(final String id) { this.id = id; }

    @Override
    public String getId() { return this.id; }

    public String getName() { return name; }

    public void setName(final String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(final String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }

    public void setPrice(final BigDecimal price) { this.price = price; }


}
