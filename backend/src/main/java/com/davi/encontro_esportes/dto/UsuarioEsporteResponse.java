package com.davi.encontro_esportes.dto;

import com.davi.encontro_esportes.entity.NivelEsporte;
import com.davi.encontro_esportes.entity.UsuarioEsporteEntity;

import java.time.LocalDate;

public record UsuarioEsporteResponse (
        Long esporteId,
        String esporte,
        NivelEsporte nivel,
        LocalDate praticaDesde
) {
    public static UsuarioEsporteResponse de(UsuarioEsporteEntity usuarioEsporte) {
        return new UsuarioEsporteResponse(usuarioEsporte.getEsporte().getId(),
                usuarioEsporte.getEsporte().getNome(),
                usuarioEsporte.getNivel(),
                usuarioEsporte.getPraticaDesde()
                );
    }
}
