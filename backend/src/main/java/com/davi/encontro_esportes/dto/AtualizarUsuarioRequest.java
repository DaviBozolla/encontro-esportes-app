package com.davi.encontro_esportes.dto;

import com.davi.encontro_esportes.entity.Genero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record AtualizarUsuarioRequest(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotNull(message = "A data de nascimento é obrigatória")
        @Past(message = "A data de nascimento inserida é inválida")
        LocalDate dataNascimento,

        @NotBlank(message = "A localização é obrigatória")
        String localizacao,

        Genero genero
) {}
