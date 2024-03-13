package com.example.productapi.lib;

public enum MessageValidation implements br.com.leverinfo.validation.ValidationMessage {
  PRODUTO_NAO_ENCONTRADO("001", "Produto não encontrado"),
  ID_OBRIGATORIO("002", "O ID é obrigatório"),
  DADOS_PRODUTO_OBRIGATORIO("003", "Os dados do produto são obrigatórios"),
  NOME_OBRIGATORIO("004", "O nome é obrigatório"),
  VALOR_OBRIGATORIO("005", "O valor é obrigatório");

  private final String codigo;
  private final String message;

  MessageValidation(String codigo, String message) {
    this.codigo = codigo;
    this.message = message;
  }

  @Override
  public String getCode() {
    return codigo;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
