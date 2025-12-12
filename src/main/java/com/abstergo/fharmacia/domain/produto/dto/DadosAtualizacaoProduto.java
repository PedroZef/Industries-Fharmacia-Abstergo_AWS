package com.abstergo.fharmacia.domain.produto.dto;

import java.math.BigDecimal;

public record DadosAtualizacaoProduto(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco
) {
}
