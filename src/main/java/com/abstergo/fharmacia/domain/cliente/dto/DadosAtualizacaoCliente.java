package com.abstergo.fharmacia.domain.cliente.dto;

import com.abstergo.fharmacia.domain.endereco.Endereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCliente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        Endereco endereco
) {
}
