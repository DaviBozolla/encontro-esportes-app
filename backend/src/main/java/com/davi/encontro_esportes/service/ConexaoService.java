package com.davi.encontro_esportes.service;

import com.davi.encontro_esportes.dto.UsuarioResponse;
import com.davi.encontro_esportes.entity.ConexaoEntity;
import com.davi.encontro_esportes.entity.UsuarioEntity;
import com.davi.encontro_esportes.repository.ConexaoRepository;
import com.davi.encontro_esportes.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConexaoService {

    private final ConexaoRepository conexaoRepository;
    private final UsuarioRepository usuarioRepository;

    public void seguir(Long seguidorId, Long seguidoId) {

        if (seguidorId.equals(seguidoId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível seguir a si mesmo");
        }

        UsuarioEntity seguidor = usuarioRepository.findById(seguidorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        UsuarioEntity seguido = usuarioRepository.findById(seguidoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        if (conexaoRepository.existsBySeguidorIdAndSeguidoId(seguidorId, seguidoId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já segue este usuário");
        }

        ConexaoEntity conexao = new ConexaoEntity();
        conexao.setSeguidor(seguidor);
        conexao.setSeguido(seguido);
        conexao.setCriadoEm(LocalDateTime.now());
        conexaoRepository.save(conexao);
    }

    public List<UsuarioResponse> listarSeguindo(Long usuarioId) {

        if (!usuarioRepository.existsById(usuarioId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        List<ConexaoEntity> conexoes = conexaoRepository.findBySeguidorId(usuarioId);

        List<UsuarioResponse> seguindo = new ArrayList<UsuarioResponse>();
        for (ConexaoEntity conexao : conexoes) {
            seguindo.add(UsuarioResponse.de(conexao.getSeguido()));
        }

        return seguindo;
    }

    public List<UsuarioResponse> listarSeguidores(Long usuarioId) {

        if (!usuarioRepository.existsById(usuarioId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        List<ConexaoEntity> conexoes = conexaoRepository.findBySeguidoId(usuarioId);

        List<UsuarioResponse> seguidores = new ArrayList<UsuarioResponse>();

        for (ConexaoEntity conexao : conexoes) {
            seguidores.add(UsuarioResponse.de(conexao.getSeguidor()));
        }

        return seguidores;
    }

    public void removerConexao(Long seguidorId, Long seguidoId) {

        ConexaoEntity conexao = conexaoRepository.findBySeguidorIdAndSeguidoId(seguidorId, seguidoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conexão não encontrada"));

        conexaoRepository.delete(conexao);
    }
}