package com.clinicasorridente1.apisorridente.controller;

import com.clinicasorridente1.apisorridente.entity.Dentista;
import com.clinicasorridente1.apisorridente.service.DentistaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/dentista")
public class DentistaController {

    @Autowired
    private DentistaService dentistaService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dentista salvar(@RequestBody Dentista dentista) {
        return dentistaService.salvar(dentista);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Dentista buscarPorId(@PathVariable("id") Integer id) {
        return dentistaService.buscarPorId(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Dentista não encontrado.")
        );
    }

    @GetMapping
    public List<Dentista> buscarTodos() {
        return dentistaService.buscarTodos();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirPorId(@PathVariable("id") Integer id) {
        dentistaService.buscarPorId(id)
                .map(dentista -> {
                    dentistaService.excluirPorId(dentista.getId());
                    return Void.TYPE;
                }).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Dentista não encontrado.")
                );
    }

    @PutMapping("/{id}")
    public void atualizar(@PathVariable("id") Integer id, @RequestBody Dentista dentista) {
        dentistaService.buscarPorId(id)
                .map(dentistaDaBase -> {
                    modelMapper.map(dentista, dentistaDaBase);
                    dentistaService.salvar(dentistaDaBase);
                    return Void.TYPE;
                }).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Dentista não encontrado.")
                );

    }


}
