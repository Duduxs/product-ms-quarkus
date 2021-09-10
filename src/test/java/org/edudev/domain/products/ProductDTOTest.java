package org.edudev.domain.products;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.WebApplicationException;
import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductDTOTest {

    private final Product product = new Product("Test product", "Test Description", BigDecimal.valueOf(24.56));

    @Test
    @DisplayName("ProductDTO must be instantiable")
    public void createProductDTO(){
        var dto = new ProductDTO(product);
        assertProductEquals(product, dto);
    }

    @Test
    @DisplayName("ProductDTO must update a product instance")
    public void updateProduct(){
        var dto = new ProductDTO(product);
        var newProduct = new Product(dto.getId());

        dto.update(newProduct);
        assertProductEquals(newProduct, dto);
    }

    @Test
    @DisplayName("Must not update for passing a negative product price")
    public void mustThrowErrorByNotPositivePrice(){
        var product = new Product("Product name", "Product description", ZERO);
        var dto = new ProductDTO(product);

        assertThrows(
                WebApplicationException.class,
                () -> dto.update(product)
        );
    }

    @Test
    @DisplayName("Must not update for passing blank to properties not blank ")
    public void mustThrowErrorByPassBlank(){
        var product = new Product("", "", ONE);
        var dto = new ProductDTO(product);

        assertThrows(
                WebApplicationException.class,
                () -> dto.update(product)
        );
    }

    @Test
    @DisplayName("Must not update for passing null to properties not null ")
    public void mustThrowErrorByPassNull(){
        var product = new Product(null, null, ONE);
        var dto = new ProductDTO(product);

        assertThrows(
                WebApplicationException.class,
                () -> dto.update(product)
        );
    }

    public void assertProductEquals(final Product product, final ProductDTO productDTO) {
        assertEquals(product.getId(), productDTO.getId());
        assertEquals(product.getName(), productDTO.getName());
        assertEquals(product.getDescription(), productDTO.getDescription());
        assertEquals(product.getPrice(), productDTO.getPrice());
    }
}
