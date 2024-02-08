package org.andino.service.product;

import org.andino.service.shared.PostgreSQLContainer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

/// For Removal and testing multiple
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@PostgreSQLContainer
@Testcontainers
class ProductRepositoryDuplicateTest {


    @Autowired
    ProductRepository productRepository;


    @Test
    void testNonEmptyProductWithMultipleProducts() {
        productRepository.save(new Product(new ProductId(1L), new Quantity(new BigDecimal(1), "number")));
        productRepository.save(new Product(new ProductId(2L), new Quantity(new BigDecimal(5), "litre")));

        Optional<Product> product = productRepository.findById(new ProductId(2L));

        assertThat(product).isNotEmpty();
        assertThat(product.get().getQuantity().getCount()).isEqualByComparingTo(new BigDecimal(5));
        assertThat(product.get().getQuantity().getUnit()).isEqualTo("litre");
    }

    @AfterEach
    void cleanup() {
        productRepository.deleteAll();
    }

}