package com.example.igorfv.product_api.application.dto;

import com.example.igorfv.product_api.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AlteracaoProdutoDTO {

  @Size(max = 100)
  private String nome;

  @Size(max = 200)
  private String descricao;

  @DecimalMin(value = "0.0", inclusive = false)
  private BigDecimal preco;

  @Min(0)
  private Integer quantidade;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private LocalDateTime changedAt;

  public static AlteracaoProdutoDTO map(Produto produto) {
    AlteracaoProdutoDTO alteracaoProdutoDTO = new AlteracaoProdutoDTO();

    alteracaoProdutoDTO.setNome(produto.getNome());
    alteracaoProdutoDTO.setDescricao(produto.getDescricao());
    alteracaoProdutoDTO.setPreco(produto.getPreco());
    alteracaoProdutoDTO.setQuantidade(produto.getQuantidade());
    alteracaoProdutoDTO.setChangedAt(produto.getCreatedAt());

    return alteracaoProdutoDTO;
  }
}
