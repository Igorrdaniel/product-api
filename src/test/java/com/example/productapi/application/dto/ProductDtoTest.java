package com.example.productapi.application.dto;

import static br.com.leverinfo.test.ValidationAssertions.assertThatRequiredArgumentException;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.productapi.domain.model.Product;
import com.example.productapi.lib.MessageValidation;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductDtoTest {

  private Product product;

  @BeforeEach
  void setUp() {

    product = new Product("Alicate", BigDecimal.TEN);
  }

  @Test
  @DisplayName("Deve mapear um produto")
  void testMap() {
    ProductDto productDto = ProductDto.map(product);

    assertThat(productDto.getId()).isEqualTo(product.getId());
    assertThat(productDto.getNome()).isEqualTo(product.getNome());
    assertThat(productDto.getValor()).isEqualTo(product.getValor());
  }

  @Test
  @DisplayName("Deve lançar exceção ao tentar mapear um produto inválido")
  void testMap_invalido() {
    assertThatRequiredArgumentException()
        .isThrownBy(() -> ProductDto.map(null))
        .withValidationMessage(MessageValidation.DADOS_PRODUTO_OBRIGATORIO);
  }
}
