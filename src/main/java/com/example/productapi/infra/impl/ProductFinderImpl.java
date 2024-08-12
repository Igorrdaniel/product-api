package com.example.productapi.infra.impl;

import br.com.leverinfo.validation.ArgumentValidations;
import br.com.leverinfo.validation.exception.NotFoundException;
import com.example.productapi.application.dto.ProductDto;
import com.example.productapi.domain.finder.ProductFinder;
import com.example.productapi.domain.model.Product;
import com.example.productapi.domain.repository.ProductRepository;
import com.example.productapi.lib.MessageValidation;
import org.springframework.stereotype.Service;

@Service
public class ProductFinderImpl implements ProductFinder {

  private final ProductRepository productRepository;

  public ProductFinderImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public ProductDto buscar(Long id) {
    ArgumentValidations.isNotNull(id, MessageValidation.ID_OBRIGATORIO);

    Product product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException(MessageValidation.PRODUTO_NAO_ENCONTRADO));

    return ProductDto.map(product);
  }
}
