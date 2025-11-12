package com.example.igorfv.product_api.application.api;

import com.example.igorfv.product_api.application.dto.AlteracaoProdutoDTO;
import com.example.igorfv.product_api.application.dto.ProdutoDTO;
import com.example.igorfv.product_api.application.service.GerenciadorProduto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("produto")
@CrossOrigin(origins = "http://localhost:4200")
public class ProdutoController {

  private final GerenciadorProduto gerenciadorProduto;

  public ProdutoController(GerenciadorProduto gerenciadorProduto) {
    this.gerenciadorProduto = gerenciadorProduto;
  }

  @Operation(description = "Endpoint para buscar todos os produtos.")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Produtos encontrados")})
  @GetMapping("/buscar")
  public List<ProdutoDTO> buscar() {
    return gerenciadorProduto.buscar();
  }

  @Operation(
      summary = "Busca um produto por id",
      description = "Busca um produto no sistema pelo id fornecido.",
      responses = {
        @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso"),
      })
  @GetMapping("/buscar/{id}")
  public ProdutoDTO buscarPorId(@Parameter(description = "ID do produto") @PathVariable UUID id) {
    return gerenciadorProduto.buscarPorId(id);
  }

  @Operation(
      summary = "Inclui um novo produto",
      description = "Inclui um novo produto no sistema com os dados fornecidos.",
      responses = {
        @ApiResponse(responseCode = "201", description = "Produto incluido com sucesso"),
      })
  @PostMapping("/incluir")
  public ProdutoDTO incluir(
      @Parameter(description = "Dados do produto") @RequestBody ProdutoDTO produtoDTO) {
    return gerenciadorProduto.incluir(produtoDTO);
  }

  @Operation(
      summary = "Altera um produto já existente",
      description = "Altera um produto já existente no sistema.",
      responses = {
        @ApiResponse(responseCode = "201", description = "Produto alterado com sucesso"),
      })
  @PatchMapping("/alterar")
  public AlteracaoProdutoDTO alterar(
      @Parameter(description = "Id do produto") @PathVariable UUID id,
      @Parameter(description = "Dados de alteração do produto") @Valid
          AlteracaoProdutoDTO alteracaoProdutoDTO) {
    return gerenciadorProduto.alterar(id, alteracaoProdutoDTO);
  }

  @Operation(
      summary = "Exclui um produto",
      description = "Exclui um produto.",
      responses = {
        @ApiResponse(responseCode = "204", description = "Produto excluido com sucesso"),
      })
  @DeleteMapping("/excluir/{id}")
  public ResponseEntity<String> excluir(
      @Parameter(description = "Id do produto") @PathVariable UUID id) {
    gerenciadorProduto.excluir(id);
    return ResponseEntity.ok("Produto excluido com sucesso");
  }
}
