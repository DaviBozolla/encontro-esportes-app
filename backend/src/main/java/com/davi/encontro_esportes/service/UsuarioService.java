package com.davi.encontro_esportes.service;

import com.davi.encontro_esportes.dto.AtualizarUsuarioRequest;
import com.davi.encontro_esportes.dto.CadastroUsuarioRequest;
import com.davi.encontro_esportes.dto.UsuarioResponse;
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

        return UsuarioResponse.de(usuarioRepository.save(usuario));
    }

    public UsuarioResponse buscarPorId(Long id) {
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        return UsuarioResponse.de(usuario);
    }

    public UsuarioResponse atualizar(Long id, AtualizarUsuarioRequest request) {
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        usuario.setNome(request.nome());
        usuario.setDataNascimento(request.dataNascimento());
        usuario.setLocalizacao(request.localizacao());
        usuario.setGenero(request.genero());

        return UsuarioResponse.de(usuarioRepository.save(usuario));
    }

    public void remover(Long id) {

        if (!usuarioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        usuarioRepository.deleteById(id);
    }
}
