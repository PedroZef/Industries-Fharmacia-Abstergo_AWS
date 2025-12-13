package com.abstergo.fharmacia.domain.endereco.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
        @NotBlank String logradouro,
        @NotBlank String numero,
        String complemento,
        @NotBlank String bairro,
        @NotBlank String cidade,
        @NotBlank String uf,
        @NotBlank @Pattern(regexp = "\\d{5}-?\\d{3}", message = "CEP deve ter o formato 00000-000") String cep) {
}
