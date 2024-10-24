package com.example.productapi.domain.finder;

import com.example.productapi.domain.model.Product;

public interface ProductFinder {

  Product buscar(Long id);
}
