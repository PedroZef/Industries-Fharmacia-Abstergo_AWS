package com.abstergo.fharmacia.domain.cliente;

import com.abstergo.fharmacia.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "clientes")
@Entity(name = "Cliente")
@Getter
@Setter
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
}
