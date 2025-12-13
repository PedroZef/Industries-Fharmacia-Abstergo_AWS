package com.abstergo.fharmacia.domain.cliente.dto;

import com.abstergo.fharmacia.domain.endereco.dto.DadosEndereco;

public record DadosDetalhamentoCliente(
        Long id,
        String nome,
        String email,
        String cpf,
        String telefone,
        DadosEndereco endereco) {
}
