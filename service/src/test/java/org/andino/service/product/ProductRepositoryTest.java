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

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = NONE)
@PostgreSQLContainer
class ProductRepositoryTest {


    @Autowired
    ProductRepository productRepository;


    @Test
    void testEmptyProduct() {
        productRepository.save(new Product(new ProductId(1L), new Quantity(new BigDecimal(1), "number")));

        Optional<Product> product = productRepository.findById(new ProductId(1L));

        assertThat(product).isNotEmpty();
        assertThat(product.get().getQuantity().getCount()).isEqualByComparingTo(new BigDecimal(1));
    }

    @AfterEach
    void cleanup() {
        productRepository.deleteAll();
    }

}