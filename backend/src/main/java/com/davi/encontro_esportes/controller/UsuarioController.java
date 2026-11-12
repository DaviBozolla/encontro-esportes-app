package com.davi.encontro_esportes.controller;

import com.davi.encontro_esportes.dto.AtualizarUsuarioRequest;
import com.davi.encontro_esportes.dto.CadastroUsuarioRequest;
import com.davi.encontro_esportes.dto.UsuarioResponse;
import com.davi.encontro_esportes.entity.Genero;
import com.davi.encontro_esportes.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable Long id) {
        UsuarioResponse usuarioEncontrado = usuarioService.buscarPorId(id);

        return ResponseEntity.status(HttpStatus.OK).body(usuarioEncontrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(@PathVariable Long id,
                                                     @Valid @RequestBody AtualizarUsuarioRequest usuario) {

        UsuarioResponse usuarioAtualizado = usuarioService.atualizar(id, usuario);

        return ResponseEntity.status(HttpStatus.OK).body(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {

        usuarioService.remover(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> buscar(@RequestParam(required = false) String nome,
                                                        @RequestParam(required = false) Genero genero,
                                                        @RequestParam(required = false) String localizacao,
                                                        @RequestParam(required = false) Integer idadeMin,
                                                        @RequestParam(required = false) Integer idadeMax,
                                                        @RequestParam(required = false) Long usuarioId
                                                        ){
        List<UsuarioResponse> usuariosEncontrados = usuarioService.buscar(nome, genero, localizacao, idadeMin,
                idadeMax, usuarioId);

        return ResponseEntity.status(HttpStatus.OK).body(usuariosEncontrados);
    }
}
