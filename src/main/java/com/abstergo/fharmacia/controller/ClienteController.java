package com.abstergo.fharmacia.controller;

import com.abstergo.fharmacia.domain.cliente.Cliente;
import com.abstergo.fharmacia.domain.cliente.dto.DadosAtualizacaoCliente;
import com.abstergo.fharmacia.domain.cliente.dto.DadosCadastroCliente;
import com.abstergo.fharmacia.domain.cliente.dto.DadosDetalhamentoCliente;
import com.abstergo.fharmacia.domain.endereco.dto.DadosEndereco;
import com.abstergo.fharmacia.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCliente> cadastrar(@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBuilder) {
        var cliente = new Cliente(dados);
        repository.save(cliente);

        var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();

        var endereco = cliente.getEndereco();
        var dadosEndereco = new DadosEndereco(endereco.getLogradouro(), endereco.getNumero(), endereco.getComplemento(),
                endereco.getBairro(), endereco.getCidade(), endereco.getUf(), endereco.getCep());

        var detalhes = new DadosDetalhamentoCliente(cliente.getId(), cliente.getNome(), cliente.getEmail(),
                cliente.getCpf(), cliente.getTelefone(), dadosEndereco);
        return ResponseEntity.created(uri).body(detalhes);
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoCliente>> listar() {
        var clientes = repository.findAll().stream()
                .map(c -> {
                    var endereco = c.getEndereco();
                    var dadosEndereco = new DadosEndereco(endereco.getLogradouro(), endereco.getNumero(), endereco.getComplemento(),
                            endereco.getBairro(), endereco.getCidade(), endereco.getUf(), endereco.getCep());
                    return new DadosDetalhamentoCliente(c.getId(), c.getNome(), c.getEmail(), c.getCpf(), c.getTelefone(), dadosEndereco);
                })
                .toList();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoCliente> detalhar(@PathVariable Long id) {
        var cliente = repository.findById(id).orElseThrow(jakarta.persistence.EntityNotFoundException::new);
        var endereco = cliente.getEndereco();
        var dadosEndereco = new DadosEndereco(endereco.getLogradouro(), endereco.getNumero(), endereco.getComplemento(),
                endereco.getBairro(), endereco.getCidade(), endereco.getUf(), endereco.getCep());
        var detalhes = new DadosDetalhamentoCliente(cliente.getId(), cliente.getNome(), cliente.getEmail(),
                cliente.getCpf(), cliente.getTelefone(), dadosEndereco);
        return ResponseEntity.ok(detalhes);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCliente> atualizar(@RequestBody @Valid DadosAtualizacaoCliente dados) {
        var cliente = repository.findById(dados.id()).orElseThrow(jakarta.persistence.EntityNotFoundException::new);
        cliente.atualizarInformacoes(dados);
        repository.save(cliente);

        var endereco = cliente.getEndereco();
        var dadosEndereco = new DadosEndereco(endereco.getLogradouro(), endereco.getNumero(), endereco.getComplemento(),
                endereco.getBairro(), endereco.getCidade(), endereco.getUf(), endereco.getCep());
        var detalhes = new DadosDetalhamentoCliente(cliente.getId(), cliente.getNome(), cliente.getEmail(),
                cliente.getCpf(), cliente.getTelefone(), dadosEndereco);
        return ResponseEntity.ok(detalhes);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
