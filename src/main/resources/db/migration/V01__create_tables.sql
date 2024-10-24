CREATE TABLE product
(
    id    bigint GENERATED ALWAYS AS IDENTITY,
    nome  VARCHAR(255)   NOT NULL,
    valor NUMERIC(19, 2) NOT NULL,

    CONSTRAINT pk_product PRIMARY KEY (id)
);