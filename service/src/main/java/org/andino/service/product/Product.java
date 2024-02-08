package org.andino.service.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product", schema = "schema_name")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @EmbeddedId
    @AttributeOverride(name = "id", column = @Column(name = "id"))
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ProductId id;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "count", column = @Column(name = "quantity")),
            @AttributeOverride(name = "unit", column = @Column(name = "quantity_unit"))
    })
    private Quantity quantity;
}

