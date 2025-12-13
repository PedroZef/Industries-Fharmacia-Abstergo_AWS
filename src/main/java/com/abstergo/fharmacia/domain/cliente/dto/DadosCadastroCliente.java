package com.abstergo.fharmacia.domain.cliente.dto;

import com.abstergo.fharmacia.domain.endereco.dto.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DadosCadastroCliente(
                @NotBlank(message = "Nome do cliente é obrigatório") @Size(min = 3, max = 100) String nome,

                @NotBlank @Email String email,

                @NotBlank String cpf,

                @NotBlank String telefone,

                @NotNull @Valid DadosEndereco endereco) {
}
