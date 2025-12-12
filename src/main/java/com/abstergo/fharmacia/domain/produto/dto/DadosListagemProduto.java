package com.abstergo.fharmacia.domain.produto.dto;

import com.abstergo.fharmacia.domain.produto.Produto;

import java.math.BigDecimal;

public record DadosListagemProduto(
        Long id,
        String nome,
        BigDecimal preco,
        String fabricante
) {
    public DadosListagemProduto(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getPreco(), produto.getFabricante());
    }
}
