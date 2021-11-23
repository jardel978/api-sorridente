package com.clinicasorridente1.apisorridente.service;

import com.clinicasorridente1.apisorridente.entity.Endereco;
import com.clinicasorridente1.apisorridente.entity.Paciente;
import com.clinicasorridente1.apisorridente.repository.IEnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private IEnderecoRepository enderecoRepository;


    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Optional<Endereco> buscarPorId(Integer id) {
       return enderecoRepository.findById(id);
    }

    public List<Endereco> buscarTodos() {
        return enderecoRepository.findAll();
    }

    public void excluirPorId(Integer id){
       enderecoRepository.deleteById(id);
    }

}
