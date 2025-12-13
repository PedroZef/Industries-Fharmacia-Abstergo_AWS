package com.abstergo.fharmacia.domain.produto;

import com.abstergo.fharmacia.domain.produto.dto.DadosAtualizacaoProduto;
import com.abstergo.fharmacia.domain.produto.dto.DadosCadastroProduto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "produtos")
@Entity(name = "Produto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String fabricante;

    @Column(name = "quantidade_estoque")
    private int quantidadeEstoque;

    public Produto(DadosCadastroProduto dados) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.preco = dados.preco();
        this.fabricante = dados.fabricante();
        this.quantidadeEstoque = dados.quantidadeEstoque();
    }

    public void atualizarInformacoes(DadosAtualizacaoProduto dados) {
        if (dados.nome() != null && !dados.nome().isBlank()) {
            this.nome = dados.nome();
        }
        if (dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if (dados.preco() != null) {
            this.preco = dados.preco();
        }
    }

}
