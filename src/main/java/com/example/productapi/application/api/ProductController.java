package com.example.productapi.application.api;

import com.example.productapi.application.dto.ProductDto;
import com.example.productapi.application.service.impl.GerenciadorProductImpl;
import com.example.productapi.domain.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tags(value = {@Tag(name = "Produtos", description = "Gerenciamento de produtos")})
@RestController
@RequestMapping("/produtos")
public class ProductController {

  private final GerenciadorProductImpl gerenciadorProduct;

  public ProductController(GerenciadorProductImpl gerenciadorProduct) {
    this.gerenciadorProduct = gerenciadorProduct;
  }

  @Operation(
      description = "Inclui um novo produto",
      tags = {"Produtos"})
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Produto registrado")})
  @PostMapping
  public ProductDto incluir(
      @Parameter(description = "Dados do produto") @Valid @RequestBody ProductDto dadosProduto) {
    return gerenciadorProduct.incluir(dadosProduto);
  }

  @Operation(
      description = "Busca um produto",
      tags = {"Produtos"})
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Produto encontrado")})
  @GetMapping(value = "{id}")
  public Product buscar(@Parameter(description = "id do produto") @PathVariable Long id) {
    return gerenciadorProduct.buscar(id);
  }

  @Operation(
      description = "Buscar todos os produtos",
      tags = {"Produtos"})
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Produtos encontrados")})
  @GetMapping()
  public List<Product> buscarTodos() {
    return gerenciadorProduct.buscarTodos();
  }
}
