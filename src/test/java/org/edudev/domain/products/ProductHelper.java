package org.edudev.domain.products;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.String.format;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductHelper {

    public void assertProductEquals(final Product product, final ProductDTO productDTO) {
        assertEquals(product.getId(), productDTO.getId());
        assertEquals(product.getName(), productDTO.getName());
        assertEquals(product.getDescription(), productDTO.getDescription());
        assertEquals(product.getPrice(), productDTO.getPrice());
    }

    public void assertProductCollectionEqualsIgnoringOrder(final Collection<Product> products, final Collection<ProductDTO> productsDTO) {

        final var sortedProducts = products.stream()
                .sorted(comparing(Product::getId))
                .collect(toList());

        final var sortedDTOs = productsDTO.stream()
                .sorted(comparing(ProductDTO::getId))
                .collect(toList());


        assertEquals(sortedProducts.size(), sortedDTOs.size());

        for (int i = 0 ; i < sortedProducts.size() ; i++ ) {
            assertProductEquals(sortedProducts.get(i), sortedDTOs.get(i));
        }
    }

    public void assertProductCollectionEquals(final Collection<Product> products, final Collection<ProductDTO> productsDTO) {
        final List<Product> productsList = new ArrayList<>(products);
        final List<ProductDTO> productsDTOList = new ArrayList<>(productsDTO);

        assertEquals(productsList.size(), productsDTOList.size());

        for (int i = 0 ; i < productsList.size() ; i++ ) {
            assertProductEquals(productsList.get(i), productsDTOList.get(i));
        }
    }

    public static Product toProductInstances(Integer i) {
        return new Product(
                String.valueOf(i),
                format("Product name %d ", i),
                format("Product description %d", i),
                BigDecimal.valueOf(i)
        );
    }

}
