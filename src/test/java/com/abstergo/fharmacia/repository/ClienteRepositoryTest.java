package com.abstergo.fharmacia.repository;

import com.abstergo.fharmacia.domain.cliente.Cliente;
import com.abstergo.fharmacia.domain.cliente.dto.DadosCadastroCliente;
import com.abstergo.fharmacia.domain.endereco.dto.DadosEndereco;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository repository;

    @Test
    @DisplayName("Deve salvar e buscar cliente por ID")
    void salvarEBuscarCliente() {
        var dadosEndereco = new DadosEndereco("Rua A", "123", "", "Centro", "Cidade", "SP", "01000-000");
        var dadosCliente = new DadosCadastroCliente("João da Silva", "joao@example.com", "12345678909", "11999999999",
                dadosEndereco);
        Cliente cliente = new Cliente(dadosCliente);
        Cliente salvo = repository.save(cliente);

        var encontrado = repository.findById(salvo.getId());

        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getNome()).isEqualTo("João da Silva");
    }

}
