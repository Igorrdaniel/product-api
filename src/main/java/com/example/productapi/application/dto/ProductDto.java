package com.example.productapi.application.dto;

import br.com.leverinfo.validation.ArgumentValidations;
import com.example.productapi.domain.model.Product;
import com.example.productapi.lib.MessageValidation;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductDto {

  private Long id;
  private String nome;
  private BigDecimal valor;

  public static ProductDto map(Product dadosProduto) {
    ArgumentValidations.isNotNull(dadosProduto, MessageValidation.DADOS_PRODUTO_OBRIGATORIO);

    ProductDto productDto = new ProductDto();

    productDto.setId(dadosProduto.getId());
    productDto.setNome(dadosProduto.getNome());
    productDto.setValor(dadosProduto.getValor());

    return productDto;
  }
}
