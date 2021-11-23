package com.clinicasorridente1.apisorridente.controller;

import com.clinicasorridente1.apisorridente.entity.Usuario;
import com.clinicasorridente1.apisorridente.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Access;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody Usuario usuario) {
        return usuarioService.salvar(usuario);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario buscarPorId(@PathVariable("id") Integer id) {
        return usuarioService.buscarPorId(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.")
        );
    }

    @GetMapping
    public List<Usuario> buscarTodos() {
        return usuarioService.buscarTodos();
    }

    @DeleteMapping("/{id}")
    public void excluirPorId(@PathVariable("id") Integer id) {
        usuarioService.buscarPorId(id)
                .map(usuario -> {
                    usuarioService.excluirPorId(usuario.getId());
                    return Void.TYPE;
                }).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.")
                );
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable("id") Integer id, @RequestBody Usuario usuario) {
        usuarioService.buscarPorId(id)
                .map(usuarioDaBase -> {
                    modelMapper.map(usuario, usuarioDaBase);
                    usuarioService.salvar(usuarioDaBase);
                    return Void.TYPE;
                }).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.")
                );
    }

}
