package com.example.productapi.application.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.example.productapi.application.dto.ProductDto;
import com.example.productapi.domain.finder.ProductFinder;
import com.example.productapi.domain.model.Product;
import com.example.productapi.domain.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GerenciadorProductImplTest {

  @InjectMocks private GerenciadorProductImpl gerenciadorProduct;

  @Mock private ProductRepository productRepository;

  @Mock private ProductFinder productFinder;

  private Product product;

  private Product product1;

  @BeforeEach
  void setUp() {
    product = new Product("Arroz", BigDecimal.valueOf(30.30));
    product1 = new Product("Macarrão", BigDecimal.valueOf(55.55));
  }

  @Test
  @DisplayName("Deve incluir um novo produto")
  void testIncluir() {
    ProductDto productDto = ProductDto.map(product);

    ProductDto productCreated = gerenciadorProduct.incluir(productDto);

    assertThat(productCreated.getId()).isEqualTo(productDto.getId());
    assertThat(productCreated.getNome()).isEqualTo(productDto.getNome());
    assertThat(productCreated.getValor()).isEqualTo(productDto.getValor());
  }

  @Test
  @DisplayName("Deve buscar todos os produtos")
  void testBuscar() {
    when(productRepository.findAll()).thenReturn(List.of(product, product1));

    List<ProductDto> productDto = gerenciadorProduct.buscarTodos();

    assertThat(productDto.get(0)).isEqualTo(ProductDto.map(product));
    assertThat(productDto.get(1)).isEqualTo(ProductDto.map(product1));
  }

  @Test
  @DisplayName("Deve buscar o produto pro ID")
  void testBuscar_porId() {
    product.setId(1L);

    when(productFinder.buscar(product.getId())).thenReturn(product);

    ProductDto productFinded = gerenciadorProduct.buscar(product.getId());

    assertThat(productFinded.getId()).isEqualTo(product.getId());
  }
}
