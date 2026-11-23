package com.davi.encontro_esportes.controller;

import com.davi.encontro_esportes.dto.AdicionarEsporteRequest;
import com.davi.encontro_esportes.dto.EsporteResponse;
import com.davi.encontro_esportes.dto.UsuarioEsporteResponse;
import com.davi.encontro_esportes.service.EsporteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EsporteController {

    private final EsporteService esporteService;

    @GetMapping("/esportes")
    public ResponseEntity<List<EsporteResponse>> listarEsportes() {
        List<EsporteResponse> esportes = esporteService.listar();
        return ResponseEntity.ok(esportes);
    }

    @PostMapping("/usuarios/{usuarioId}/esportes")
    public ResponseEntity<UsuarioEsporteResponse> adicionarEsporte (@PathVariable Long usuarioId,
                                                                    @Valid @RequestBody AdicionarEsporteRequest request) {
        UsuarioEsporteResponse esporte = esporteService.adicionar(usuarioId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(esporte);
    }

    @GetMapping("/usuarios/{usuarioId}/esportes")
    public ResponseEntity<List<UsuarioEsporteResponse>> listarDoUsuario (@PathVariable Long usuarioId) {
        List<UsuarioEsporteResponse> esportes = esporteService.listarDoUsuario(usuarioId);
        return ResponseEntity.ok(esportes);
    }

    @DeleteMapping("/usuarios/{usuarioId}/esportes/{esporteId}")
    public ResponseEntity<Void> remover(@PathVariable Long usuarioId, @PathVariable Long esporteId) {
        esporteService.remover(usuarioId, esporteId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
