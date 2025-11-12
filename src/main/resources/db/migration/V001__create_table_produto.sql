CREATE TABLE produtos(
    id  uuid  NOT NULL ,
    nome varchar(100) NOT NULL,
    descricao varchar(200),
    preco numeric(10, 2) NOT NULL,
    quantidade integer NOT NULL,
    created_at timestamptz NOT NULL,

    CONSTRAINT pk_produto PRIMARY KEY(id)

);