package com.example.productapi.application.dto;

import com.example.productapi.domain.model.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductDto {

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private Long id;

  @NotBlank
  private String nome;
  private BigDecimal valor;

  public static ProductDto map(Product product) {
    ProductDto productDto = new ProductDto();

    productDto.setId(product.getId());
    productDto.setNome(product.getNome());
    productDto.setValor(product.getValor());

    return productDto;
  }
}
