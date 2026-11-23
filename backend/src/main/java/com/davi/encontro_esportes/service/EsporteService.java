package com.davi.encontro_esportes.service;

import com.davi.encontro_esportes.dto.AdicionarEsporteRequest;
import com.davi.encontro_esportes.dto.EsporteResponse;
import com.davi.encontro_esportes.dto.UsuarioEsporteResponse;
import com.davi.encontro_esportes.entity.EsporteEntity;
import com.davi.encontro_esportes.entity.UsuarioEntity;
import com.davi.encontro_esportes.entity.UsuarioEsporteEntity;
import com.davi.encontro_esportes.repository.EsporteRepository;
import com.davi.encontro_esportes.repository.UsuarioEsporteRepository;
import com.davi.encontro_esportes.repository.UsuarioRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EsporteService {

    private final EsporteRepository esporteRepository;
    private final UsuarioEsporteRepository usuarioEsporteRepository;
    private final UsuarioRepository usuarioRepository;

    public List<EsporteResponse> listar() {
        List<EsporteEntity> esportes = esporteRepository.findAll();
        List<EsporteResponse> resposta = new ArrayList<EsporteResponse>();

        for (EsporteEntity esporte : esportes) {
            resposta.add(EsporteResponse.de(esporte));
        }

        return resposta;
    }

    public UsuarioEsporteResponse adicionar(Long usuarioId, AdicionarEsporteRequest request) {
        UsuarioEntity usuario = usuarioRepository.findById((usuarioId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        EsporteEntity esporte = esporteRepository.findById(request.esporteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Esporte não encontrado"));

        if (usuarioEsporteRepository.existsByUsuarioIdAndEsporteId(usuarioId, request.esporteId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Esse esporte já está no perfil");
        }
        else {
            UsuarioEsporteEntity usuarioEsporte = new UsuarioEsporteEntity();
            usuarioEsporte.setEsporte(esporte);
            usuarioEsporte.setUsuario(usuario);
            usuarioEsporte.setNivel(request.nivel());
            usuarioEsporte.setPraticaDesde(request.praticaDesde());

            return UsuarioEsporteResponse.de(usuarioEsporteRepository.save(usuarioEsporte));
        }
    }

    public List<UsuarioEsporteResponse> listarDoUsuario(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        List<UsuarioEsporteEntity> esportes = usuarioEsporteRepository.findByUsuarioId(usuarioId);
        List<UsuarioEsporteResponse> resposta = new ArrayList<UsuarioEsporteResponse>();
        for (UsuarioEsporteEntity esporte : esportes) {
            resposta.add(UsuarioEsporteResponse.de(esporte));
        }

        return resposta;
    }

    public void remover(Long usuarioId, Long esporteId) {
        UsuarioEsporteEntity usuarioEsporte = usuarioEsporteRepository.findByUsuarioIdAndEsporteId(usuarioId, esporteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Esse esporte não está no perfil"));

        usuarioEsporteRepository.delete(usuarioEsporte);
    }
}
