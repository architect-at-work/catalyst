package org.andino.service.product;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "product", schema = "schema_name")
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@EmbeddedId
	@AttributeOverride(name = "id", column = @Column(name = "id"))
	@GeneratedValue(strategy = IDENTITY)
	private ProductId id;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "count", column = @Column(name = "quantity")),
			@AttributeOverride(name = "unit", column = @Column(name = "quantity_unit")) })
	private Quantity quantity;

}
