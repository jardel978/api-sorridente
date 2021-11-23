package com.clinicasorridente1.apisorridente.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_CONSULTA")
@SequenceGenerator(name = "consulta", sequenceName = "SQ_TB_CONSULTA", allocationSize = 1)
public class Consulta implements Serializable {

    @Id
    @Column(name = "id_consulta")
    @GeneratedValue(generator = "consulta", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @OneToOne // relacionamento 1:1
    @JoinColumn(name = "id_paciente", nullable = false, unique = true,
            foreignKey = @ForeignKey(name = "fk_paciente")) // indica a FK
            // desse atributo
    // (temos que criar
    // um atributo do tipo dessa classe "Consulta" na classe de onde vem a FK, nesse caso: "Paciente")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_dentista", nullable = false, unique = true, foreignKey = @ForeignKey(name = "fk_dentista"))
    private Dentista dentista;

 /*   private Usuario usuario;*/


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_consulta")
    private Date dataConsulta = new Date(System.currentTimeMillis());

}
