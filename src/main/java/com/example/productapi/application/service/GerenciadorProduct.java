package com.example.productapi.application.service;

import com.example.productapi.application.dto.ProductDto;
import com.example.productapi.domain.model.Product;
import java.util.List;

public interface GerenciadorProduct {

  ProductDto incluir(ProductDto dadosProduto);

  Product buscar(Long id);

  List<Product> buscarTodos();
}
