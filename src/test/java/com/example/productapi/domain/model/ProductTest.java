package com.example.productapi.domain.model;

import static br.com.leverinfo.test.ValidationAssertions.assertThatInvalidArgumentException;
import static br.com.leverinfo.test.ValidationAssertions.assertThatRequiredArgumentException;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.productapi.lib.MessageValidation;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductTest {

  @Test
  @DisplayName("Deve criar um product")
  void testProduct() {
    Product product = new Product("Teste", BigDecimal.TEN);
    product.setId(1L);

    assertThat(product.getId()).isEqualTo(1L);
    assertThat(product.getNome()).isEqualTo("Teste");
    assertThat(product.getValor()).isEqualTo(BigDecimal.TEN);
  }

  @Test
  @DisplayName("Deve lançar exceção ao tentar criar um produto com dados inválidos")
  void testProduct_Invalido() {
    assertThatRequiredArgumentException()
        .isThrownBy(() -> new Product(null, BigDecimal.TEN))
        .withValidationMessage(MessageValidation.NOME_OBRIGATORIO);

    assertThatInvalidArgumentException()
        .isThrownBy(() -> new Product("", BigDecimal.TEN))
        .withValidationMessage(MessageValidation.NOME_OBRIGATORIO);

    assertThatRequiredArgumentException()
        .isThrownBy(() -> new Product("Teste", null))
        .withValidationMessage(MessageValidation.VALOR_OBRIGATORIO);
  }
}
