package com.davi.encontro_esportes.repository;

import com.davi.encontro_esportes.entity.ParticipacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ParticipacaoRepository extends JpaRepository<ParticipacaoEntity, Long> {

    boolean existsByPartidaIdAndUsuarioId(Long partidaId, Long usuarioId);

    Optional<ParticipacaoEntity> findByPartidaIdAndUsuarioId(Long partidaId, Long usuarioId);

    Long countByPartidaId(Long partidaId);

    List<ParticipacaoEntity> findByPartidaId(Long partidaId);

    void deleteByPartidaId(Long partidaId);

    void deleteByUsuarioId(Long usuarioId);

    List<ParticipacaoEntity> findByUsuarioIdAndPartidaDataHoraAfterOrderByPartidaDataHoraAsc(Long usuarioId, LocalDateTime agora);
}
