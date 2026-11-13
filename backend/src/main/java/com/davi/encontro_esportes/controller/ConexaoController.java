package com.davi.encontro_esportes.controller;

import com.davi.encontro_esportes.dto.UsuarioResponse;
import com.davi.encontro_esportes.service.ConexaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios/{usuarioId}")
@RequiredArgsConstructor
public class ConexaoController {

    private final ConexaoService conexaoService;

    @PostMapping("/seguindo/{seguidoId}")
    public ResponseEntity<Void> seguir(@PathVariable Long usuarioId, @PathVariable Long seguidoId) {
        conexaoService.seguir(usuarioId, seguidoId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/seguindo")
    public ResponseEntity<List<UsuarioResponse>> seguindo(@PathVariable Long usuarioId) {
        List<UsuarioResponse> seguindo = conexaoService.listarSeguindo(usuarioId);
        return ResponseEntity.ok(seguindo);
    }

    @GetMapping("/seguidores")
    public ResponseEntity<List<UsuarioResponse>> seguidores(@PathVariable Long usuarioId) {
        List<UsuarioResponse> seguidores = conexaoService.listarSeguidores(usuarioId);
        return ResponseEntity.ok(seguidores);
    }

    @DeleteMapping("/seguindo/{seguidoId}")
    public ResponseEntity<Void> deixarSeguir(@PathVariable Long usuarioId, @PathVariable Long seguidoId) {
        conexaoService.removerConexao(usuarioId, seguidoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/seguidores/{seguidorId}")
    public ResponseEntity<Void> removerSeguidor(@PathVariable Long usuarioId, @PathVariable Long seguidorId) {
        conexaoService.removerConexao(seguidorId, usuarioId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
