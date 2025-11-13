package com.example.igorfv.product_api.application.service;

import com.example.igorfv.product_api.application.dto.AlteracaoProdutoDTO;
import com.example.igorfv.product_api.application.dto.ProdutoDTO;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface GerenciadorProduto {

  List<ProdutoDTO> buscar();

  ProdutoDTO buscarPorId(UUID id);

  ProdutoDTO incluir(@Valid ProdutoDTO produtoDTO);

  ProdutoDTO alterar(UUID id, @Valid AlteracaoProdutoDTO alteracaoProdutoDTO);

  void excluir(UUID id);
}
