package com.davi.encontro_esportes.repository;

import com.davi.encontro_esportes.entity.EsporteEntity;
import com.davi.encontro_esportes.entity.UsuarioEsporteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioEsporteRepository extends JpaRepository<UsuarioEsporteEntity, Long> {

    boolean existsByUsuarioIdAndEsporteId(Long usuarioId, Long esporteId);

    Optional<UsuarioEsporteEntity> findByUsuarioIdAndEsporteId(Long usuarioId, Long esporteId);

    List<UsuarioEsporteEntity> findByUsuarioId(Long usuarioId);

    void deleteByUsuarioId(Long usuarioId);
}
