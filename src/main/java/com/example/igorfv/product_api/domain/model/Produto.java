package com.example.igorfv.product_api.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "produtos")
@Getter
@Setter
@EqualsAndHashCode
public class Produto {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(unique = true)
  private UUID id;

  @Column(name = "nome", nullable = false, length = 100)
  private String nome;

  @Column(name = "descricao", length = 200)
  private String descricao;

  @Column(name = "preco", nullable = false)
  private BigDecimal preco;

  @Column(name = "quantidade", nullable = false)
  private Integer quantidade;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  public Produto(
      UUID id,
      String nome,
      String descricao,
      BigDecimal preco,
      Integer quantidade,
      LocalDateTime createdAt) {
    this.id = id;
    this.nome = nome;
    this.descricao = descricao;
    this.preco = preco;
    this.quantidade = quantidade;
    this.createdAt = createdAt;
  }

  protected Produto() {
    super();
  }
}
