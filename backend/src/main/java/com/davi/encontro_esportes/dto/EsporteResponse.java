package com.davi.encontro_esportes.dto;

import com.davi.encontro_esportes.entity.EsporteEntity;

public record EsporteResponse (
        Long id,
        String nome
) {
    public static EsporteResponse de(EsporteEntity esporte) {
           return new EsporteResponse(esporte.getId(),
                   esporte.getNome());
    }
}
