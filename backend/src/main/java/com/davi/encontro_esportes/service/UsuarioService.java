package com.davi.encontro_esportes.service;

import com.davi.encontro_esportes.dto.CadastroUsuarioRequest;
import com.davi.encontro_esportes.dto.UsuarioResponse;
import com.davi.encontro_esportes.entity.UsuarioEntity;
import com.davi.encontro_esportes.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioResponse cadastrar(CadastroUsuarioRequest request) {
        if (usuarioRepository.existsByEmail(request.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já cadastrado");
        }

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome(request.nome());
        usuario.setDataNascimento(request.dataNascimento());
        usuario.setLocalizacao(request.localizacao());
        usuario.setEmail(request.email());
        usuario.setSenha(request.senha());
        usuario.setGenero(request.genero());

        UsuarioEntity usuarioSalvo = usuarioRepository.save(usuario);

        return UsuarioResponse.de(usuarioSalvo);
    }

    public UsuarioEntity atualizar(UsuarioEntity usuario) {
        UsuarioEntity usuarioAntigo = usuarioRepository.findById(usuario.getId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        usuarioAntigo.setNome(usuario.getNome());
        usuarioAntigo.setGenero(usuario.getGenero());
        usuarioAntigo.setDataNascimento(usuario.getDataNascimento());
        usuarioAntigo.setEmail(usuario.getEmail());
        usuarioAntigo.setLocalizacao(usuario.getLocalizacao());
        usuarioAntigo.setSenha(usuario.getSenha());

        return usuarioRepository.save(usuarioAntigo);
    }

    public void remover(Long id) {

        if (!usuarioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        usuarioRepository.deleteById(id);
    }
}
