package br.com.dbc.vemser.projetoTelecomunicacoes.entity;

import br.com.dbc.vemser.projetoTelecomunicacoes.entity.planos.TipoDePlano;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa")
    @SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa2", allocationSize = 1)
    @Column(name = "id_cliente")
    private Integer idPessoa;

    @Column(name = "nome")
    private String nome;

    @Column(name = "dt_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "email")
    private String email;

    @Column(name = "numero_telefone")
    private Long numeroTelefone;

    @Column(name = "tipo_plano")
    private TipoDePlano tipoDePlano;

    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "tb_cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Fatura> faturas;

    @OneToMany(mappedBy = "tb_cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Endereco> enderecos;

}
