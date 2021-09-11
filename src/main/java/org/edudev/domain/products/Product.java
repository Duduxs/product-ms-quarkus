package org.edudev.domain.products;

import dev.morphia.annotations.*;
import org.edudev.arch.domain.DomainEntity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

import static dev.morphia.utils.IndexType.TEXT;
import static java.math.BigDecimal.ONE;

@Indexes(@Index(fields = @Field(value = "$**", type = TEXT)))
@Entity(value = "products", useDiscriminator = false)
public final class Product implements DomainEntity {

    @Id
    private final String id;

    @NotBlank(message = "Name é obrigatório")
    private String name;

    @NotBlank(message = "Description é obrigatório")
    private String description;

    @NotNull
    @Min(value = 1, message = "Preço deve ser positivo")
    private BigDecimal price;

    Product() {
        this(UUID.randomUUID().toString(), "", "", ONE);
    }

    public Product(final String id) {
        this.id = id;
    }

    public Product(final String name, final String description, final BigDecimal price) {
        this(UUID.randomUUID().toString(), name, description, price);
    }

    public Product(final String id, final String name, final String description, final BigDecimal price) {
        this.id = id;
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
