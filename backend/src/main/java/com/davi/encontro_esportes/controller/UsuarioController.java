package com.davi.encontro_esportes.controller;

import com.davi.encontro_esportes.dto.CadastroUsuarioRequest;
import com.davi.encontro_esportes.dto.UsuarioResponse;
import com.davi.encontro_esportes.entity.UsuarioEntity;
import com.davi.encontro_esportes.service.UsuarioService;
import jakarta.validation.Valid;
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
    public ResponseEntity<UsuarioResponse> cadastrar(@Valid @RequestBody CadastroUsuarioRequest usuario) {

        UsuarioResponse novoUsuario = usuarioService.cadastrar(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioEntity> atualizar(@RequestBody UsuarioEntity usuario, @PathVariable Long id) {

        UsuarioEntity usuarioAtualizado = usuarioService.atualizar(usuario);

        return ResponseEntity.status(HttpStatus.OK).body(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {

        usuarioService.remover(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
