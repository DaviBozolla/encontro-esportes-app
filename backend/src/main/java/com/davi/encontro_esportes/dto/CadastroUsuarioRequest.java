package com.davi.encontro_esportes.dto;

import com.davi.encontro_esportes.entity.Genero;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CadastroUsuarioRequest(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotNull(message = "A data de nascimento é obrigatória")
        @Past(message = "A data de nascimento informada é inválida")
        LocalDate dataNascimento,

        @NotBlank(message = "A localização é obrigatória")
        String localizacao,

        @NotBlank(message = "O email é obrigatório")
        @Email(message = "O email informado é inválido")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[^A-Za-z0-9]).{7,}$",
                message = "A senha deve conter no mínimo 7 caracteres, com pelo menos um número e um símbolo"
        )
        String senha,

        Genero genero
) {}


