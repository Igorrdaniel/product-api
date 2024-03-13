package com.example.productapi.domain.model;

import br.com.leverinfo.validation.ArgumentValidations;
import com.example.productapi.lib.MessageValidation;
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
    ArgumentValidations.isNotBlank(nome, MessageValidation.NOME_OBRIGATORIO);
    ArgumentValidations.isNotNull(valor, MessageValidation.VALOR_OBRIGATORIO);

    this.nome = nome;
    this.valor = valor;
  }

  protected Product() {
    super();
  }
}
