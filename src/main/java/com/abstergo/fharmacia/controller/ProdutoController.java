package com.abstergo.fharmacia.controller;

import com.abstergo.fharmacia.domain.produto.Produto;
import com.abstergo.fharmacia.domain.produto.dto.DadosAtualizacaoProduto;
import com.abstergo.fharmacia.domain.produto.dto.DadosCadastroProduto;
import com.abstergo.fharmacia.domain.produto.dto.DadosListagemProduto;
import com.abstergo.fharmacia.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroProduto dados, UriComponentsBuilder uriBuilder) {
        var produto = new Produto(null, dados.nome(), dados.descricao(), dados.preco(), dados.fabricante(), dados.quantidadeEstoque());
        repository.save(produto);

        var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();

        return ResponseEntity.created(uri).body(produto);
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemProduto>> listar() {
        var produtos = repository.findAll().stream()
                .map(p -> new DadosListagemProduto(p.getId(), p.getNome(), p.getPreco(), p.getFabricante()))
                .toList();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var produto = repository.getReferenceById(id);
        return ResponseEntity.ok(produto);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody DadosAtualizacaoProduto dados) {
        var produto = repository.getReferenceById(dados.id());

        if (dados.nome() != null) {
            produto.setNome(dados.nome());
        }
        if (dados.descricao() != null) {
            produto.setDescricao(dados.descricao());
        }
        if (dados.preco() != null) {
            produto.setPreco(dados.preco());
        }

        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
