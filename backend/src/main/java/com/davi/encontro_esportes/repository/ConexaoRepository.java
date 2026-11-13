package com.davi.encontro_esportes.repository;

import com.davi.encontro_esportes.entity.ConexaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConexaoRepository extends JpaRepository<ConexaoEntity, Long> {

    boolean existsBySeguidorIdAndSeguidoId(Long seguidorId, Long seguidoId);

    Optional<ConexaoEntity> findBySeguidorIdAndSeguidoId(Long seguidorId, Long seguidoId);

    List<ConexaoEntity> findBySeguidoId(Long seguidoId);

    List<ConexaoEntity> findBySeguidorId(Long seguidorId);

    void deleteBySeguidorIdOrSeguidoId(Long seguidorId, Long seguidoId);
}