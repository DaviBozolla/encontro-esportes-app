package com.davi.encontro_esportes.controller;

import com.davi.encontro_esportes.dto.CriarPartidaRequest;
import com.davi.encontro_esportes.dto.PartidaResponse;
import com.davi.encontro_esportes.service.PartidaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PartidaController {

    private final PartidaService partidaService;

    @PostMapping("/usuarios/{usuarioId}/partidas")
    public ResponseEntity<PartidaResponse> criarPartida(@PathVariable Long usuarioId,
                                                        @Valid @RequestBody CriarPartidaRequest request) {
        PartidaResponse partida = partidaService.criar(usuarioId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(partida);
    }

    @DeleteMapping("/usuarios/{usuarioId}/partidas/{partidaId}")
    public ResponseEntity<Void> cancelarPartida(@PathVariable Long usuarioId, @PathVariable Long partidaId) {
        partidaService.cancelar(usuarioId, partidaId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/partidas/{partidaId}/participantes/{usuarioId}")
    public ResponseEntity<PartidaResponse> entrarPartida(@PathVariable Long usuarioId, @PathVariable Long partidaId) {
        PartidaResponse entrarPartida = partidaService.entrar(usuarioId, partidaId);
        return ResponseEntity.status(HttpStatus.CREATED).body(entrarPartida);
    }

    @DeleteMapping("/partidas/{partidaId}/participantes/{usuarioId}")
    public ResponseEntity<Void> sairPartida(@PathVariable Long usuarioId, @PathVariable Long partidaId) {
        partidaService.sair(usuarioId, partidaId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
