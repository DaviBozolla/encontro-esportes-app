package com.davi.encontro_esportes.dto;

import com.davi.encontro_esportes.entity.Genero;

import java.time.LocalDate;

public record AtualizarUsuarioRequest(
        String nome,
        LocalDate dataNascimento,
        String localizacao,
        Genero genero
) {}
