package com.example.igorfv.product_api.application.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.igorfv.product_api.application.dto.AlteracaoProdutoDTO;
import com.example.igorfv.product_api.application.dto.ProdutoDTO;
import com.example.igorfv.product_api.domain.finder.ProdutoFinder;
import com.example.igorfv.product_api.domain.model.Produto;
import com.example.igorfv.product_api.domain.repository.ProdutoRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GerenciadorProdutoImplTest {

  @Mock private ProdutoRepository produtoRepository;

  @Mock private ProdutoFinder produtoFinder;

  @InjectMocks private GerenciadorProdutoImpl gerenciadorProduto;

  @Captor private ArgumentCaptor<Produto> captor;

  private Produto produto;

  @BeforeEach
  protected void setUp() {
    produto =
        new Produto(
            UUID.fromString("18801ed0-00b7-4be0-86ef-d65d8b457507"),
            "produto teste",
            "descricao teste",
            BigDecimal.TEN,
            10,
            LocalDateTime.of(2025, 11, 13, 10, 10));

    configurarMocks();
  }

  @DisplayName("Deve buscar todos os produtos")
  @Test
  void buscar() {
    List<ProdutoDTO> produtoDTO = gerenciadorProduto.buscar();

    assertEquals(produtoDTO, Collections.singletonList(ProdutoDTO.map(produto)));
  }

  @DisplayName("Deve buscar um produto pro ID")
  @Test
  void buscarPorId() {
    when(produtoFinder.buscar(produto.getId())).thenReturn(produto);
    ProdutoDTO produtoDTO = gerenciadorProduto.buscarPorId(produto.getId());

    assertEquals(produtoDTO, ProdutoDTO.map(produto));
  }

  @DisplayName("Deve Incluir um novo produto")
  @Test
  void incluir() {
    ProdutoDTO produtoDTO = new ProdutoDTO();

    produtoDTO.setId(UUID.fromString("18801ed0-00b7-4be0-86ef-d65d8b457507"));
    produtoDTO.setNome("teste");
    produtoDTO.setDescricao("descricao teste");
    produtoDTO.setPreco(BigDecimal.TEN);
    produtoDTO.setQuantidade(10);
    produtoDTO.setCreatedAt(LocalDateTime.of(2025, 11, 13, 10, 10));

    ProdutoDTO produtoDTOIncluido = gerenciadorProduto.incluir(produtoDTO);

    verify(produtoRepository).save(captor.capture());

    Produto produtoIncluido = captor.getValue();

    assertEquals(UUID.fromString("18801ed0-00b7-4be0-86ef-d65d8b457507"), produtoIncluido.getId());
    assertEquals("teste", produtoIncluido.getNome());
    assertEquals("descricao teste", produtoIncluido.getDescricao());
    assertEquals(BigDecimal.TEN, produtoIncluido.getPreco());
    assertEquals(10, produtoIncluido.getQuantidade());

    assertEquals(produtoDTOIncluido, ProdutoDTO.map(produtoIncluido));
  }

  @DisplayName("Deve alterar um produto")
  @Test
  void update() {
    when(produtoFinder.buscar(produto.getId())).thenReturn(produto);

    AlteracaoProdutoDTO alteracaoProdutoDTO = new AlteracaoProdutoDTO();

    alteracaoProdutoDTO.setNome("Teste 2");

    AlteracaoProdutoDTO produtoAlteradoDTO =
        gerenciadorProduto.alterar(produto.getId(), alteracaoProdutoDTO);

    verify(produtoRepository, times(1)).save(captor.capture());

    Produto produtoAlterado = captor.getValue();

    assertEquals("Teste 2", produtoAlterado.getNome());
    assertEquals(produtoAlteradoDTO, AlteracaoProdutoDTO.map(produtoAlterado));
  }

  @DisplayName("Deve excluir um produto")
  @Test
  void deleteById() {
    when(produtoFinder.buscar(produto.getId())).thenReturn(produto);

    gerenciadorProduto.excluir(produto.getId());

    verify(produtoRepository).delete(produto);
  }

  private void configurarMocks() {
    lenient().when(produtoRepository.findAll()).thenReturn(Collections.singletonList(produto));
  }
}
