package com.davi.encontro_esportes.dto;

import com.davi.encontro_esportes.entity.Genero;
import com.davi.encontro_esportes.entity.UsuarioEntity;

import java.time.LocalDate;

public record UsuarioResponse(
        Long id,
        String nome,
        LocalDate dataNascimento,
        String localizacao,
        String email,
        Genero genero
) {
    public static UsuarioResponse de(UsuarioEntity usuario) {
        return new UsuarioResponse(usuario.getId(),
                usuario.getNome(),
                usuario.getDataNascimento(),
                usuario.getLocalizacao(),
                usuario.getEmail(),
                usuario.getGenero());
    }
}
