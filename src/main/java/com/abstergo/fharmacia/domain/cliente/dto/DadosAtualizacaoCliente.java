package com.abstergo.fharmacia.domain.cliente.dto;

import com.abstergo.fharmacia.domain.endereco.dto.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCliente(
                @NotNull Long id,
                String nome,
                String telefone,
                @Valid DadosEndereco endereco) {
}
