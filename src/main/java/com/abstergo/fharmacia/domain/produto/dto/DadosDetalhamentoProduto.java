package com.abstergo.fharmacia.domain.produto.dto;

import java.math.BigDecimal;

public record DadosDetalhamentoProduto(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        String fabricante,
        int quantidadeEstoque) {
}
