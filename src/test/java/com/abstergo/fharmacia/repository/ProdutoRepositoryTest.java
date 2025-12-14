package com.abstergo.fharmacia.repository;

import com.abstergo.fharmacia.domain.produto.dto.DadosCadastroProduto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.abstergo.fharmacia.domain.produto.Produto;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class ProdutoRepositoryTest {
    @Autowired
    private ProdutoRepository repository;

    @Test
    @DisplayName("Deve salvar e buscar produto por ID")
    void salvarEBuscarProduto() {
        Produto produto = new Produto(new DadosCadastroProduto("Dipirona", "Analgésico", new BigDecimal("12.50"), "Medley", 100));
        Produto salvo = repository.save(produto);

        var encontrado = repository.findById(salvo.getId());

        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getNome()).isEqualTo("Dipirona");
    }


}
