package com.example.igorfv.product_api.domain.finder;

import com.example.igorfv.product_api.domain.model.Produto;

import java.util.UUID;

public interface ProdutoFinder {

  Produto buscar(UUID id);
}
