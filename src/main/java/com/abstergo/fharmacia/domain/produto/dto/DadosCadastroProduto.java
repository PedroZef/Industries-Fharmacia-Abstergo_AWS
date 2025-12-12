package com.abstergo.fharmacia.domain.produto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosCadastroProduto(
        @NotBlank
        String nome,
        String descricao,
        @NotNull
        BigDecimal preco,
        @NotBlank
        String fabricante,
        int quantidadeEstoque
) {
}
