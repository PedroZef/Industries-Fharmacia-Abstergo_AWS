package com.abstergo.fharmacia.domain.produto.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;

public record DadosAtualizacaoProduto(
                @NotNull Long id,
                String nome,
                String descricao,
                @DecimalMin(value = "0.0", inclusive = false) BigDecimal preco) {
}
