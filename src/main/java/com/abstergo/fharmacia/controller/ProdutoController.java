package com.abstergo.fharmacia.controller;

import com.abstergo.fharmacia.domain.produto.Produto;
import com.abstergo.fharmacia.domain.produto.dto.DadosAtualizacaoProduto;
import com.abstergo.fharmacia.domain.produto.dto.DadosCadastroProduto;
import com.abstergo.fharmacia.domain.produto.dto.DadosDetalhamentoProduto;
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
        var produto = new Produto(dados);
        repository.save(produto);

        var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();

        var detalhes = new DadosDetalhamentoProduto(produto.getId(), produto.getNome(), produto.getDescricao(),
                produto.getPreco(), produto.getFabricante(), produto.getQuantidadeEstoque());
        return ResponseEntity.created(uri).body(detalhes);
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoProduto>> listar() {
        var produtos = repository.findAll().stream()
                .map(p -> new DadosDetalhamentoProduto(p.getId(), p.getNome(), p.getDescricao(), p.getPreco(),
                        p.getFabricante(), p.getQuantidadeEstoque()))
                .toList();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var produto = repository.findById(id).orElseThrow(jakarta.persistence.EntityNotFoundException::new);
        var detalhes = new DadosDetalhamentoProduto(produto.getId(), produto.getNome(), produto.getDescricao(),
                produto.getPreco(), produto.getFabricante(), produto.getQuantidadeEstoque());
        return ResponseEntity.ok(detalhes);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoProduto dados) {
        var produto = repository.findById(dados.id()).orElseThrow(jakarta.persistence.EntityNotFoundException::new);
        produto.atualizarInformacoes(dados);
        repository.save(produto);

        var detalhes = new DadosDetalhamentoProduto(produto.getId(), produto.getNome(), produto.getDescricao(),
                produto.getPreco(), produto.getFabricante(), produto.getQuantidadeEstoque());
        return ResponseEntity.ok(detalhes);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
