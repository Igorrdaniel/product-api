package com.example.igorfv.product_api.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

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
}
