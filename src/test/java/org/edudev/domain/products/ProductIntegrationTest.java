package org.edudev.domain.products;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import org.edudev.core.QuarkusIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.valueOf;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@QuarkusIntegrationTest
@TestHTTPEndpoint(ProductsService.class)
public class ProductIntegrationTest {

    @Inject
    private Products products;

    private final ProductHelper helper = new ProductHelper();

    private final Collection<Product> data = Stream.of(1, 2, 3, 4, 5)
            .map(ProductHelper::toProductInstances)
            .collect(toList());

    @Test
    @DisplayName("Must be able to insert product")
    public void mustCreateProduct() {
        final Product product = new Product("Product Insert", "Product Description", valueOf(59L));

        try {
            final ProductDTO insertedProduct = given()
                    .contentType(JSON)
                    .body(new ProductDTO(product))
                    .when().post()
                    .then().statusCode(201)
                    .extract().as(ProductDTO.class);

            helper.assertProductEquals(product, insertedProduct);
        } finally {
            products.remove(product);
        }
    }

    @Test
    @DisplayName("Must throw conflict by product already inserted")
    public void mustThrowConflictAtInsert() {
        final Product product = new Product("1", "Product name", "Product description", ONE);

        try {
            products.insert(product);
            given()
                    .contentType(JSON).body(product)
                    .when().post()
                    .then().statusCode(409);
        } finally {
            products.remove(product);
        }
    }

    @Test
    @DisplayName("Must be able to update product")
    public void mustUpdateProduct() {
        final Product product = new Product("1", "Product Update", "Product Description", valueOf(49.54));

        try {
            products.insert(product);
            final Product productWithNewData = new Product("1", "Product with new name", "Product with new description", valueOf(30));

            final ProductDTO returnedProduct = given()
                    .contentType(JSON).body(new ProductDTO(productWithNewData))
                    .when().put(product.getId())
                    .then().statusCode(200)
                    .extract().as(ProductDTO.class);

            helper.assertProductEquals(productWithNewData, returnedProduct);
        } finally {
            products.remove(product);
        }
    }

    @Test
    @DisplayName("Must trow not found by unknown id in update")
    public void mustThrowNotFoundAtUpdate() {
        final Product product = new Product("1", "Product Update", "Product Description", valueOf(49.54));

        given()
                .contentType(JSON).body(new ProductDTO(product))
                .when().put(product.getId())
                .then().statusCode(404);
    }

    @Test
    @DisplayName("Must be able to remove product")
    public void mustRemoveProduct() {
        final Product product = new Product("Product Remove", "Product Description", valueOf(13.92));

        try {
            products.insert(product);
            given()
                    .contentType(JSON).when().delete(product.getId()).then().statusCode(204);
        } finally {
            assertFalse(products.findById(product.getId()).isPresent());
        }
    }

    @Test
    @DisplayName("Must trow not found by unknown id in remove")
    public void mustThrowNotFoundAtRemove() {
        final Product product = new Product("Product Remove", "Product Description", valueOf(13.92));

        given()
                .contentType(JSON).body(new ProductDTO(product))
                .when().put(product.getId())
                .then().statusCode(404);
    }

    @Test
    @DisplayName("Must be able to find product by id")
    public void mustFindProductById() {
        final Product product = new Product("Product Find By Id", "Product Description", valueOf(99.43));

        try {
            products.insert(product);

            final ProductDTO returnedProduct = given()
                    .contentType(JSON).when()
                    .get(product.getId()).then()
                    .statusCode(200).extract().as(ProductDTO.class);

            helper.assertProductEquals(product, returnedProduct);
        } finally {
            products.remove(product);
        }
    }

    @Test
    @DisplayName("Must throw not found by unknown id in find by id")
    public void mustThrowNotFoundAtFindById() {
        final Product product = new Product("Product Find By Id", "Product Description", valueOf(99.43));
        given()
                .contentType(JSON).when()
                .get(product.getId()).then()
                .statusCode(404);

    }

    @Test
    @DisplayName("Must be able to list product")
    public void mustListProduct() {
        try {
            data.forEach(products::insert);

            final Collection<ProductDTO> returnedDTOs = given()
                    .contentType(JSON).when()
                    .get().then()
                    .statusCode(200).extract()
                    .jsonPath().getList(".", ProductDTO.class);

            helper.assertProductCollectionEqualsIgnoringOrder(data, returnedDTOs);
        } finally {
            data.forEach(products::remove);
        }
    }

    @Test
    @DisplayName("Must be able to search product without any query params")
    public void mustSearchProduct() {
        try {
            data.forEach(products::insert);

            final Collection<ProductDTO> returnedDTOs = given()
                    .contentType(JSON).when()
                    .get("/search").then()
                    .statusCode(200).extract()
                    .jsonPath().getList(".", ProductDTO.class);

            helper.assertProductCollectionEqualsIgnoringOrder(data, returnedDTOs);

        } finally {
            data.forEach(products::remove);
        }
    }

    @Test
    @DisplayName("Must be able to search product with query param [q]")
    public void mustSearchProductByQueryParam() {
        try {
            data.forEach(products::insert);

            final List<ProductDTO> returnedDTO = given()
                    .contentType(JSON).when()
                    .get("/search?q=Product name 3").then()
                    .statusCode(200).extract().
                    jsonPath().getList(".", ProductDTO.class);

            assertEquals(returnedDTO.size(), 1);

            final var product = data.stream()
                    .filter(i -> i.getName().equals(returnedDTO.get(0).getName()))
                    .findFirst()
                    .get();

            helper.assertProductEquals(product, returnedDTO.get(0));

        } finally {
            data.forEach(products::remove);
        }
    }

    @Test
    @DisplayName("Must be able to search product with query param [q, min_price, max_price]")
    public void mustSearchProductByQueryParams() {
        try {
            data.forEach(products::insert);

            final List<ProductDTO> returnedDTO = given()
                    .contentType(JSON).when()
                    .get("/search?q=Product name&min_price=2&max_price=4")
                    .then()
                    .statusCode(200).extract()
                    .jsonPath().getList(".", ProductDTO.class);

            final Collection<Product> product = data.stream()
                    .filter(e -> e.getPrice().intValue() >= 2 && e.getPrice().intValue() <= 4)
                    .collect(toList());

            helper.assertProductCollectionEqualsIgnoringOrder(product, returnedDTO);

        } finally {
            data.forEach(products::remove);
        }
    }

    @Test
    @DisplayName("Must be able to search product with query param [q, first, size, order]")
    public void mustSearchProductByQueryParamsPageable() {
        try {
            data.forEach(products::insert);

            final List<ProductDTO> returnedDTO = given()
                    .contentType(JSON).when()
                    .get("/search?q=Product description&first=2&size=3&order=ASC")
                    .then()
                    .statusCode(200).extract()
                    .jsonPath().getList(".", ProductDTO.class);

            final Collection<Product> product = data.stream()
                    .skip(2)
                    .limit(3)
                    .collect(toList());

            helper.assertProductCollectionEqualsIgnoringOrder(product, returnedDTO);

        } finally {
            data.forEach(products::remove);
        }
    }

    @Test
    @DisplayName("Must be able to search product with query param [field, order]")
    public void mustSearchProductByQueryParamsSortable() {
        try {
            data.forEach(products::insert);

            final List<ProductDTO> returnedDTO = given()
                    .contentType(JSON).when()
                    .get("/search?field=price&order=DESC")
                    .then()
                    .statusCode(200).extract()
                    .jsonPath().getList(".", ProductDTO.class);

            final Collection<Product> product = data.stream()
                    .sorted(comparing(Product::getPrice).reversed())
                    .collect(toList());

            helper.assertProductCollectionEquals(product, returnedDTO);

        } finally {
            data.forEach(products::remove);
        }
    }

}
