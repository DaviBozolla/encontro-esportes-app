package com.davi.encontro_esportes.dto;

import com.davi.encontro_esportes.entity.NivelEsporte;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record AdicionarEsporteRequest (
        @NotNull(message = "O esporte é obrigatório")
        Long esporteId,

        @NotNull(message = "O nível do esporte é obrigatório")
        NivelEsporte nivel,

        @PastOrPresent(message = "A data de início não pode ser no futuro")
        LocalDate praticaDesde
) {
}
