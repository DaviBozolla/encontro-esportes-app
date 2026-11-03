package com.davi.encontro_esportes.controller;

import com.davi.encontro_esportes.entity.UsuarioEntity;
import com.davi.encontro_esportes.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioEntity> cadastrar(@RequestBody UsuarioEntity usuario) {

        UsuarioEntity novoUsuario = usuarioService.cadastrar(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {

        usuarioService.remover(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
