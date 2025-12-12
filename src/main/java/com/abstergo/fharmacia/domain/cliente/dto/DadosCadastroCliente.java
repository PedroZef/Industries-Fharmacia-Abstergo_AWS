package com.abstergo.fharmacia.domain.cliente.dto;

import com.abstergo.fharmacia.domain.endereco.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record DadosCadastroCliente(
        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @CPF
        String cpf,

        @NotBlank
        String telefone,

        @NotNull
        @Valid
        Endereco endereco
) {
}
