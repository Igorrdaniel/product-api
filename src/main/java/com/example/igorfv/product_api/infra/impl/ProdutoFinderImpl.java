package com.example.igorfv.product_api.infra.impl;

import com.example.igorfv.product_api.domain.finder.ProdutoFinder;
import com.example.igorfv.product_api.domain.model.Produto;
import com.example.igorfv.product_api.domain.repository.ProdutoRepository;
import com.example.igorfv.product_api.exception.NotFoundException;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdutoFinderImpl implements ProdutoFinder {

  private final ProdutoRepository produtoRepository;

  public ProdutoFinderImpl(ProdutoRepository produtoRepository) {
    this.produtoRepository = produtoRepository;
  }

  @Transactional(readOnly = true)
  @Override
  public Produto buscar(UUID id) {
    return produtoRepository.findById(id).orElseThrow(NotFoundException::new);
  }
}
