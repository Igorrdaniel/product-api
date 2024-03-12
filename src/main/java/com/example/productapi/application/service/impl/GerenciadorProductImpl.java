package com.example.productapi.application.service.impl;

import br.com.leverinfo.validation.ArgumentValidations;
import com.example.productapi.application.dto.ProductDto;
import com.example.productapi.application.service.GerenciadorProduct;
import com.example.productapi.domain.finder.ProductFinder;
import com.example.productapi.domain.model.Product;
import com.example.productapi.domain.repository.ProductRepository;
import com.example.productapi.lib.MessageValidation;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GerenciadorProductImpl implements GerenciadorProduct {

  private final ProductRepository productRepository;
  private final ProductFinder productFinder;

  public GerenciadorProductImpl(ProductRepository productRepository, ProductFinder productFinder) {
    this.productRepository = productRepository;
    this.productFinder = productFinder;
  }

  @Transactional
  @Override
  public ProductDto incluir(ProductDto dadosProduto) {
    ArgumentValidations.isNotNull(dadosProduto, MessageValidation.DADOS_PRODUTO_OBRIGATORIO);

    Product product = new Product(dadosProduto.getNome(), dadosProduto.getValor());

    productRepository.save(product);

    return ProductDto.map(product);
  }

  @Transactional(readOnly = true)
  @Override
  public Product buscar(Long id) {
    ArgumentValidations.isNotNull(id, MessageValidation.ID_OBRIGATORIO);

    return productFinder.buscar(id);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Product> buscarTodos() {
    return productRepository.findAll();
  }
}
