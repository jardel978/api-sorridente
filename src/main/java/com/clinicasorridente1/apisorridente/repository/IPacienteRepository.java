package com.clinicasorridente1.apisorridente.repository;

import com.clinicasorridente1.apisorridente.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteRepository extends JpaRepository<Paciente, Integer> {
}
