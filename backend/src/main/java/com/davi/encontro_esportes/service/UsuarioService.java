package com.davi.encontro_esportes.service;

import com.davi.encontro_esportes.dto.AtualizarUsuarioRequest;
import com.davi.encontro_esportes.dto.CadastroUsuarioRequest;
import com.davi.encontro_esportes.dto.UsuarioResponse;
import com.davi.encontro_esportes.entity.Genero;
import com.davi.encontro_esportes.entity.UsuarioEntity;
import com.davi.encontro_esportes.repository.ConexaoRepository;
import com.davi.encontro_esportes.repository.UsuarioEsporteRepository;
import com.davi.encontro_esportes.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ConexaoRepository conexaoRepository;
    private final UsuarioEsporteRepository usuarioEsporteRepository;

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

    @Transactional
    public void remover(Long id) {

        if (!usuarioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        conexaoRepository.deleteBySeguidorIdOrSeguidoId(id, id);
        usuarioEsporteRepository.deleteByUsuarioId(id);
        usuarioRepository.deleteById(id);

    }

    public List<UsuarioResponse> buscar(String nome,
                                        Genero genero,
                                        String localizacao,
                                        Integer idadeMin,
                                        Integer idadeMax,
                                        Long usuarioId,
                                        Long esporteId) {

        if (idadeMin != null && idadeMax != null && idadeMin > idadeMax) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A idade mínima não pode ser " +
                    "maior que a idade máxima");
        }

        LocalDate nascidoDepois = null; // idade maxima
        LocalDate nascidoAte = null; // idade minima

        if (idadeMin != null) {
            nascidoAte = LocalDate.now().minusYears(idadeMin);
        }
        if (idadeMax != null) {
            nascidoDepois = LocalDate.now().minusYears(idadeMax + 1);
        }

        List<UsuarioEntity> usuarios = usuarioRepository.buscar(nome, genero, nascidoAte, nascidoDepois,
                localizacao, usuarioId, esporteId);

        List<UsuarioResponse> usuariosEncontrados = new ArrayList<UsuarioResponse>();

        for (UsuarioEntity usuario : usuarios) {
            usuariosEncontrados.add(UsuarioResponse.de(usuario));
        }

        return usuariosEncontrados;
    }
}
