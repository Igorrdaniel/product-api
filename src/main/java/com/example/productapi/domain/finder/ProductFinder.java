package com.example.productapi.domain.finder;

import com.example.productapi.application.dto.ProductDto;

public interface ProductFinder {

  ProductDto buscar(Long id);
}
