CREATE TABLE product
(
    id            BIGINT NOT NULL,
    quantity      DECIMAL,
    quantity_unit VARCHAR(255),
    CONSTRAINT pk_product PRIMARY KEY (id)
);