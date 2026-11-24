package com.davi.encontro_esportes.service;

import com.davi.encontro_esportes.dto.CriarPartidaRequest;
import com.davi.encontro_esportes.dto.PartidaResponse;
import com.davi.encontro_esportes.entity.EsporteEntity;
import com.davi.encontro_esportes.entity.ParticipacaoEntity;
import com.davi.encontro_esportes.entity.PartidaEntity;
import com.davi.encontro_esportes.entity.UsuarioEntity;
import com.davi.encontro_esportes.repository.EsporteRepository;
import com.davi.encontro_esportes.repository.ParticipacaoRepository;
import com.davi.encontro_esportes.repository.PartidaRepository;
import com.davi.encontro_esportes.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PartidaService {
    private final PartidaRepository partidaRepository;
    private final ParticipacaoRepository participacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EsporteRepository esporteRepository;

    @Transactional
    public PartidaResponse criar(Long usuarioId, CriarPartidaRequest request) {
        UsuarioEntity usuario = usuarioRepository.findById((usuarioId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        EsporteEntity esporte = esporteRepository.findById(request.esporteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Esporte não encontrado"));

        PartidaEntity partida = new PartidaEntity();
        partida.setCriador(usuario);
        partida.setEsporte(esporte);
        partida.setLocal(request.local());
        partida.setDataHora(request.dataHora());
        partida.setMaxParticipantes(request.maxParticipantes());
        partida.setDescricao(request.descricao());
        partida.setCriadoEm(LocalDateTime.now());

        PartidaEntity partidaSalva = partidaRepository.save(partida);
        ParticipacaoEntity entrou = new ParticipacaoEntity();
        entrou.setPartida(partidaSalva);
        entrou.setUsuario(usuario);
        entrou.setEntrouEm(LocalDateTime.now());
        participacaoRepository.save(entrou);

        return PartidaResponse.de(partidaSalva, 1L);
    }

    public PartidaResponse entrar(Long usuarioId, Long partidaId) {
        UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));

        PartidaEntity partida = partidaRepository.findById(partidaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Partida não encontrada."));

        if (participacaoRepository.existsByPartidaIdAndUsuarioId(partidaId, usuarioId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Você já está inscrito nesta partida.");
        }

        if (partida.getDataHora().isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Essa partida já aconteceu");
        }

        if (participacaoRepository.countByPartidaId(partidaId) >= partida.getMaxParticipantes()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Essa partida está lotada.");
        }

        ParticipacaoEntity participacao = new ParticipacaoEntity();
        participacao.setUsuario(usuario);
        participacao.setPartida(partida);
        participacao.setEntrouEm(LocalDateTime.now());
        ParticipacaoEntity participacaoSalva = participacaoRepository.save(participacao);

        return PartidaResponse.de(partida, participacaoRepository.countByPartidaId(partidaId));
    }

    public void sair(Long usuarioId, Long partidaId) {
        ParticipacaoEntity participacao = participacaoRepository.findByPartidaIdAndUsuarioId(partidaId, usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Você não está inscrito nesta partida."));

        PartidaEntity partida = partidaRepository.findById(partidaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Partida não encontrada."));

        if (usuarioId.equals(partida.getCriador().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O criador não pode cancelar a partida.");
        }

        participacaoRepository.delete(participacao);
    }

    @Transactional
    public void cancelar(Long usuarioId, Long partidaId) {
        PartidaEntity partida = partidaRepository.findById(partidaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Partida não encontrada"));

        if (!usuarioId.equals(partida.getCriador().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas o criador da partida pode cancelá-la");
        }

        participacaoRepository.deleteByPartidaId(partidaId);
        partidaRepository.delete(partida);
    }
}
