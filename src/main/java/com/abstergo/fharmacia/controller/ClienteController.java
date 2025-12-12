package com.abstergo.fharmacia.controller;

import com.abstergo.fharmacia.domain.cliente.Cliente;
import com.abstergo.fharmacia.domain.cliente.dto.DadosAtualizacaoCliente;
import com.abstergo.fharmacia.domain.cliente.dto.DadosCadastroCliente;
import com.abstergo.fharmacia.domain.cliente.dto.DadosListagemCliente;
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
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBuilder) {
        var cliente = new Cliente(null, dados.nome(), dados.email(), dados.cpf(), dados.telefone(), dados.endereco());
        repository.save(cliente);

        var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemCliente>> listar() {
        var clientes = repository.findAll().stream()
                .map(c -> new DadosListagemCliente(c.getId(), c.getNome(), c.getEmail(), c.getCpf()))
                .toList();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var cliente = repository.getReferenceById(id);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoCliente dados) {
        var cliente = repository.getReferenceById(dados.id());

        if (dados.nome() != null) {
            cliente.setNome(dados.nome());
        }
        if (dados.telefone() != null) {
            cliente.setTelefone(dados.telefone());
        }
        if (dados.endereco() != null) {
            // Aqui você pode adicionar uma lógica mais refinada para atualizar o endereço
            cliente.setEndereco(dados.endereco());
        }

        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
