package com.clinicasorridente1.apisorridente.service;

import com.clinicasorridente1.apisorridente.entity.Dentista;
import com.clinicasorridente1.apisorridente.repository.IDentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {

    @Autowired
    private IDentistaRepository dentistaRepository;

    public Dentista salvar(Dentista dentista) {
        return dentistaRepository.save(dentista);
    }

    public Optional<Dentista> buscarPorId(Integer id) {
        return dentistaRepository.findById(id);
    }

    public List<Dentista> buscarTodos() {
        return dentistaRepository.findAll();
    }

    public void excluirPorId(Integer id) {
        dentistaRepository.deleteById(id);
    }

}
