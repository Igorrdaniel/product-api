package com.example.productapi.application.service;

import com.example.productapi.application.dto.ProductDto;
import java.util.List;

public interface GerenciadorProduct {

  ProductDto incluir(ProductDto dadosProduto);

  ProductDto buscar(Long id);

  List<ProductDto> buscarTodos();
}
