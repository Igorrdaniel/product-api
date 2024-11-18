package com.example.productapi.infra.impl;

import static br.com.leverinfo.test.ValidationAssertions.assertThatNotFoundException;
import static br.com.leverinfo.test.ValidationAssertions.assertThatRequiredArgumentException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.example.productapi.domain.model.Product;
import com.example.productapi.domain.repository.ProductRepository;
import com.example.productapi.lib.MessageValidation;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductFinderImplTest {

  private Product product;

  @InjectMocks private ProductFinderImpl productFinder;

  @Mock private ProductRepository productRepository;

  @BeforeEach
  void setUp() {
    product = new Product("Teste", BigDecimal.valueOf(25));
    product.setId(1L);
  }

  @Test
  @DisplayName("Deve buscar um produto pro Id")
  void testBuscadorProductImpl_Buscar() {
    when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

    Product productFinded = productFinder.buscar(product.getId());

    assertThat(productFinded).isEqualTo(product);
  }

  @Test
  @DisplayName("Deve lançar exceção ao buscar um produto com Id inválido")
  void testBuscadorProductImpl_Buscar_Invaldio() {
    assertThatRequiredArgumentException()
        .isThrownBy(() -> productFinder.buscar(null))
        .withValidationMessage(MessageValidation.ID_OBRIGATORIO);
  }

  @Test
  @DisplayName("Deve lançar exceção ao buscar um produto com Id não encontrado")
  void testBuscadorProductImpl_Buscar_NaoEncontrado() {
    Long productId = product.getId();

    assertThatNotFoundException()
        .isThrownBy(() -> productFinder.buscar(productId))
        .withValidationMessage(MessageValidation.PRODUTO_NAO_ENCONTRADO);
  }
}
