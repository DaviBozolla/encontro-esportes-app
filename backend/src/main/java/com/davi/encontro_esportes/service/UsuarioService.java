package com.davi.encontro_esportes.service;

import com.davi.encontro_esportes.entity.UsuarioEntity;
import com.davi.encontro_esportes.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioEntity cadastrar(UsuarioEntity usuario) {
        String email = usuario.getEmail();

        if (usuarioRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já cadastrado");
        }

        return usuarioRepository.save(usuario);
    }

    public void remover(Long id) {

        if (!usuarioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        usuarioRepository.deleteById(id);
    }
}
