package com.clinicasorridente1.apisorridente.controller;

import com.clinicasorridente1.apisorridente.entity.Paciente;
import com.clinicasorridente1.apisorridente.service.EnderecoService;
import com.clinicasorridente1.apisorridente.service.PacienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController()
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Paciente salvar(@RequestBody Paciente paciente) {
        return pacienteService.salvar(paciente);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Paciente buscarPorId(@PathVariable("id") Integer id) {
        return pacienteService.buscarPorId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Paciente não encontrado")
        );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Paciente> buscarTodos() {
        return pacienteService.buscarTodos();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirPorId(@PathVariable("id") Integer id) {
        pacienteService.buscarPorId(id)
                .map(paciente -> {
                    pacienteService.excluirPorId(paciente.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Paciente não encontrado")
                );
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable("id") Integer id, @RequestBody Paciente paciente) {
        pacienteService.buscarPorId(id)
                .map((pacienteDaBase) -> {
                    modelMapper.map(paciente, pacienteDaBase);
                    pacienteService.salvar(pacienteDaBase);

//                    modelMapper.map(paciente.getEndereco(), pacienteDaBase.getEndereco());
//                    enderecoService.salvar(pacienteDaBase.getEndereco());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Paciente não encontrado")
                );
    }

}
