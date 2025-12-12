package com.abstergo.fharmacia.domain.cliente.dto;

public record DadosListagemCliente(
        Long id,
        String nome,
        String email,
        String cpf
) {
}
