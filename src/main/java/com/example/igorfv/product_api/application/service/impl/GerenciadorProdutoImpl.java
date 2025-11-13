package com.example.igorfv.product_api.application.service.impl;

import com.example.igorfv.product_api.application.dto.AlteracaoProdutoDTO;
import com.example.igorfv.product_api.application.dto.ProdutoDTO;
import com.example.igorfv.product_api.application.service.GerenciadorProduto;
import com.example.igorfv.product_api.domain.finder.ProdutoFinder;
import com.example.igorfv.product_api.domain.model.Produto;
import com.example.igorfv.product_api.domain.repository.ProdutoRepository;
import com.example.igorfv.product_api.exception.DuplicatedException;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GerenciadorProdutoImpl implements GerenciadorProduto {

  private final ProdutoRepository produtoRepository;
  private final ProdutoFinder produtoFinder;

  public GerenciadorProdutoImpl(ProdutoRepository produtoRepository, ProdutoFinder produtoFinder) {
    this.produtoRepository = produtoRepository;
    this.produtoFinder = produtoFinder;
  }

  @Transactional(readOnly = true)
  @Override
  public List<ProdutoDTO> buscar() {
    return produtoRepository.findAll().stream().map(ProdutoDTO::map).toList();
  }

  @Transactional(readOnly = true)
  @Override
  public ProdutoDTO buscarPorId(UUID id) {
    return ProdutoDTO.map(produtoFinder.buscar(id));
  }

  @Transactional
  @Override
  public ProdutoDTO incluir(@Valid ProdutoDTO produtoDTO) {
    if (produtoDTO.getId() != null) {
      validarDuplicidadeProduto(produtoDTO.getId());
    }

    Produto produto =
        new Produto(
            produtoDTO.getId(),
            produtoDTO.getNome(),
            produtoDTO.getDescricao(),
            produtoDTO.getPreco(),
            produtoDTO.getQuantidade(),
            LocalDateTime.now());

    produtoRepository.save(produto);

    return ProdutoDTO.map(produto);
  }

  @Transactional
  @Override
  public ProdutoDTO alterar(UUID id, AlteracaoProdutoDTO alteracaoProdutoDTO) {
    Produto produto = produtoFinder.buscar(id);

    alterarProduto(alteracaoProdutoDTO, produto);

    produtoRepository.save(produto);

    return ProdutoDTO.map(produto);
  }

  @Transactional
  @Override
  public void excluir(UUID id) {
    Produto produto = produtoFinder.buscar(id);

    produtoRepository.delete(produto);
  }

  private static void alterarProduto(AlteracaoProdutoDTO dadosProduto, Produto produto) {
    if (Objects.nonNull(dadosProduto.getNome())) {
      produto.setNome(dadosProduto.getNome());
    }
    if (Objects.nonNull(dadosProduto.getDescricao())) {
      produto.setDescricao(dadosProduto.getDescricao());
    }
    if (Objects.nonNull(dadosProduto.getPreco())) {
      produto.setPreco(dadosProduto.getPreco());
    }
    if (Objects.nonNull(dadosProduto.getQuantidade())) {
      produto.setQuantidade(dadosProduto.getQuantidade());
    }

    produto.setCreatedAt(LocalDateTime.now());
  }

  private void validarDuplicidadeProduto(UUID id) {
    if (id != null) {
      Optional<Produto> produtoOpt = produtoRepository.findById(id);
      if (produtoOpt.isPresent()) {
        throw new DuplicatedException();
      }
    }
  }
}
