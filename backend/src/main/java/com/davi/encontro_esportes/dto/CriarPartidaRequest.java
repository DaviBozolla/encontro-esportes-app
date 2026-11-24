package com.davi.encontro_esportes.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record CriarPartidaRequest(
        @NotNull(message = "O esporte é obrigatório")
        Long esporteId,

        @NotBlank(message = "A localização é obrigatória")
        String local,

        @NotNull(message = "A data e horário são obrigatórios")
        @Future(message = "A partida deve ser marcadao para uma data futura")
        LocalDateTime dataHora,

        @NotNull(message = "O máximo de participantes é obrigatório")
        @Min(value = 2, message = "A partida deve ter no mínimo dois participantes")
        Integer maxParticipantes,

        @Size(max = 255, message = "A descrição deve conter no máximo 255 caracteres")
        String descricao
) {
}
