package com.davi.encontro_esportes.dto;

import com.davi.encontro_esportes.entity.PartidaEntity;

import java.time.LocalDateTime;

public record PartidaResponse(
        Long id,
        Long criadorId,
        String criador,
        Long esporteId,
        String esporte,
        String local,
        LocalDateTime dataHora,
        Integer maxParticipantes,
        Long participantes,
        String descricao
) {
    public static PartidaResponse de(PartidaEntity partida, Long participantes) {
        return new PartidaResponse(partida.getId(),
                partida.getCriador().getId(),
                partida.getCriador().getNome(),
                partida.getEsporte().getId(),
                partida.getEsporte().getNome(),
                partida.getLocal(),
                partida.getDataHora(),
                partida.getMaxParticipantes(),
                participantes,
                partida.getDescricao());
    }
}
