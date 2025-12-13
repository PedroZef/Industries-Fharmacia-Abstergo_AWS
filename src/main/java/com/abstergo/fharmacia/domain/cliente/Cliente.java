package com.abstergo.fharmacia.domain.cliente;

import com.abstergo.fharmacia.domain.endereco.Endereco;
import com.abstergo.fharmacia.domain.cliente.dto.DadosAtualizacaoCliente;
import com.abstergo.fharmacia.domain.cliente.dto.DadosCadastroCliente;
import jakarta.persistence.*;

import lombok.*;

@Table(name = "clientes")
@Entity(name = "Cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String cpf;

    private String telefone;

    @Embedded
    private Endereco endereco;

    public Cliente(DadosCadastroCliente dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        var d = dados.endereco();
        this.endereco = new Endereco(d.logradouro(), d.numero(), d.complemento(), d.bairro(), d.cidade(), d.uf(),
                d.cep());
    }

    public void atualizarInformacoes(DadosAtualizacaoCliente dados) {
        if (dados.nome() != null && !dados.nome().isBlank()) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null && !dados.telefone().isBlank()) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            var d = dados.endereco();
            this.endereco = new Endereco(d.logradouro(), d.numero(), d.complemento(), d.bairro(), d.cidade(), d.uf(),
                    d.cep());
        }
    }
}
