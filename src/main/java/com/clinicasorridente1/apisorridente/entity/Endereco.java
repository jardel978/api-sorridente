package com.clinicasorridente1.apisorridente.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.naming.Name;
import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_ENDERECO")
@SequenceGenerator(name = "endereco", sequenceName = "SQ_TB_ENDERECO", allocationSize = 1)
public class Endereco implements Serializable {

    @Id
    @Column(name = "id_endereco")
    @GeneratedValue(generator = "endereco", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "rua_endereco")
    private String rua;

    @Column(name = "numero_endereco")
    private String numero;

    @Column(name = "bairro_endereco")
    private String bairro;

    @Column(name = "cidade_endereco")
    private String cidade;

    @Column(name = "estado_endereco")
    private String estado;

    @JsonIgnore // para evitar um cíclo de criação de endereços e pacientes (não serializar esse atributo)
    @OneToOne(mappedBy = "endereco")
    private Paciente paciente;

}
