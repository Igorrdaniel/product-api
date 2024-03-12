package com.example.productapi.infra.impl;

import br.com.leverinfo.validation.exception.NotFoundException;
import com.example.productapi.domain.finder.ProductFinder;
import com.example.productapi.domain.model.Product;
import com.example.productapi.domain.repository.ProductRepository;
import com.example.productapi.lib.MessageValidation;
import org.springframework.stereotype.Service;

@Service
public class ProductFInderImpl implements ProductFinder {

  private final ProductRepository productRepository;

  public ProductFInderImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public Product buscar(Long id) {
    return productRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(MessageValidation.PRODUTO_NAO_ENCONTRADO));
  }
}
