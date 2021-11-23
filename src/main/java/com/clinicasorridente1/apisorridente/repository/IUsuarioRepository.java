package com.clinicasorridente1.apisorridente.repository;

import com.clinicasorridente1.apisorridente.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

}
