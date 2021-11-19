package com.clinicasorridente1.apisorridente.repository;

import com.clinicasorridente1.apisorridente.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

//@Component
public interface IEnderecoRepository extends JpaRepository<Endereco, Integer> {

}
