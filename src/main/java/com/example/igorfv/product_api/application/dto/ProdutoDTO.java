package com.example.igorfv.product_api.application.dto;

import com.example.igorfv.product_api.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Data
public class ProdutoDTO {

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private UUID id;

  @NotBlank(message = "Nome é obrigatório")
  @Size(max = 100)
  private String nome;

  @Size(max = 200)
  private String descricao;

  @NotNull(message = "Preço é obrigatório")
  @DecimalMin(value = "0.0", inclusive = false)
  private BigDecimal preco;

  @NotNull(message = "Quantidade é obrigatória")
  @Min(0)
  private Integer quantidade;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private LocalDateTime createdAt;

  public static ProdutoDTO map(Produto produto) {
    ProdutoDTO produtoDTO = new ProdutoDTO();

    produtoDTO.setId(produto.getId());
    produtoDTO.setNome(produto.getNome());
    produtoDTO.setDescricao(produto.getDescricao());
    produtoDTO.setPreco(produto.getPreco());
    produtoDTO.setQuantidade(produto.getQuantidade());
    produtoDTO.setCreatedAt(produto.getCreatedAt());

    return produtoDTO;
  }
}
