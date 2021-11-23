package com.clinicasorridente1.apisorridente.service;

import com.clinicasorridente1.apisorridente.entity.Paciente;
import com.clinicasorridente1.apisorridente.repository.IEnderecoRepository;
import com.clinicasorridente1.apisorridente.repository.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private IPacienteRepository pacienteRepository;

    @Autowired
    private IEnderecoRepository enderecoRepository;

    public Paciente salvar(Paciente paciente) {
//        enderecoRepository.save(paciente.getEndereco());
        return pacienteRepository.save(paciente);
    }

    public Optional<Paciente> buscarPorId(Integer id) {
        return pacienteRepository.findById(id);
    }

    public List<Paciente> buscarTodos() {
        return pacienteRepository.findAll();
    }

    public void excluirPorId(Integer id){
        pacienteRepository.deleteById(id);
    }

}
