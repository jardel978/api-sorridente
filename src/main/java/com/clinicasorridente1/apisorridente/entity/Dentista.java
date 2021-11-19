package com.clinicasorridente1.apisorridente.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_DENTISTA")
@SequenceGenerator(name = "dentista", sequenceName = "SQ_TB_DENTISTA", allocationSize = 1)
public class Dentista implements Serializable {

    @Id
    @Column(name = "id_dentista")
    @GeneratedValue(generator = "dentista", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "num_matricula_dentista")
    private String numMatricula;

    @Column(name = "nome_dentista")
    private String nome;

    @Column(name = "sobrenome_dentista")
    private String sobrenome;

    @OneToOne(mappedBy = "dentista")
    private Consulta consulta;

}
