package com.clinicasorridente1.apisorridente.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Data //annotation do Lombok para gerar geters, setters e toString
@AllArgsConstructor // Lombok para gerar construtor com todos os atributos da classe Paciente
@NoArgsConstructor // Lombok para gerar construtor default
@Builder // Lombok para instanciar objetos dessa classe utilizando o Pattern Builder
@Entity // indica que essa classe é uma entidade
@Table(name = "TB_PACIENTE")
@SequenceGenerator(name = "paciente", sequenceName = "SQ_TB_PACIENTE", allocationSize = 1) //sequência para gerar ids
public class Paciente implements Serializable {

    @Id //indica que esse atributo é o id
    @Column(name = "id_paciente") // nomeando coluna
    @GeneratedValue(generator = "paciente", strategy = GenerationType.SEQUENCE) // identificando a sequência de
    // geração dos ids
    private Integer id;

    @Column(name = "nome_paciente", nullable = false)
    private String nome;

    @Column(name = "sobrenome_paciente", nullable = false)
    private String sobrenome;

    @Column(name = "rg_paciente")
    private String rg;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_cadastro_paciente")
    private Calendar dataCadastro;

    @Column(name = "genero_paciente")
    private Genero genero;

    @Transient // indica que esse atributo não irá para o banco de dados
    private Integer idade;

    @OneToOne
    @JoinColumn(name = "id_endereco", nullable = false, unique = true, foreignKey = @ForeignKey(name = "fk_endereco"))
    private Endereco endereco;

    @OneToOne(mappedBy = "paciente") //fazendo o relacionamento bidirecional com consulta. O mappedBy informa que
    // esse relacionamento já foi mapeado por outra entidade e o name do atributo que o mapeou na outra entidade é
    // passado como valor
    private Consulta consulta;

}
