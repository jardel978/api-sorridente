package com.clinicasorridente1.apisorridente.controller;

import com.clinicasorridente1.apisorridente.entity.Endereco;
import com.clinicasorridente1.apisorridente.service.EnderecoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Endereco salvar(@RequestBody Endereco endereco) {
        return enderecoService.salvar(endereco);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Endereco buscarPorId(@PathVariable("id") Integer id) {
        return enderecoService.buscarPorId(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado.")
        );
    }

    @GetMapping
    public List<Endereco> buscarTodos() {
        return enderecoService.buscarTodos();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirPorId(@PathVariable("id") Integer id) {
        enderecoService.buscarPorId(id)
                .map(endereco -> {
                    enderecoService.excluirPorId(endereco.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Endereço não encontrado")
                );
    }

    @PutMapping("/{id}")
    public void atualizar(@PathVariable("id") Integer id, @RequestBody Endereco endereco) {
        enderecoService.buscarPorId(id)
                .map(enderecoDaBase -> {
                    modelMapper.map(endereco, enderecoDaBase);
                    enderecoService.salvar(enderecoDaBase);
                    return Void.TYPE;
                }).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado.")
                );
    }

}
