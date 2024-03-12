package com.example.productapi.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "product")
@Table
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  private BigDecimal valor;

  public Product(String nome, BigDecimal valor) {
    this();
    this.nome = nome;
    this.valor = valor;
  }

  protected Product() {
    super();
  }
}
