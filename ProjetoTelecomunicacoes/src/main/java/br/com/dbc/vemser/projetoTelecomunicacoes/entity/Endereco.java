package br.com.dbc.vemser.projetoTelecomunicacoes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "tb_endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_endereco")
    @SequenceGenerator(name = "seq_endereco", sequenceName = "seq_endereco", allocationSize = 1)
    @Column(name = "id_endereco")
    private Integer idEndereco;

//    @Column(name = "id_cliente")
//    private Integer idPessoa;

    @Column(name = "tipo")
    private TipoEndereco tipo;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "cep")
    private String cep;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    private String estado;

    @Column(name = "pais")
    private String pais;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Cliente cliente;
}
