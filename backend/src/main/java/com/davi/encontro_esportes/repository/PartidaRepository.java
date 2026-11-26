package com.davi.encontro_esportes.repository;

import com.davi.encontro_esportes.entity.PartidaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PartidaRepository extends JpaRepository<PartidaEntity, Long> {
    @Query("""
            SELECT p FROM PartidaEntity p
            WHERE p.dataHora > :agora 
                AND EXISTS (
                    SELECT c FROM ConexaoEntity c
                    WHERE c.seguidor.id = :usuarioId AND c.seguido = p.criador
                )
            ORDER BY p.dataHora ASC
            """)
    List<PartidaEntity> buscarFeed(@Param("usuarioId") Long usuarioId,
                                   @Param("agora") LocalDateTime agora);

    List<PartidaEntity> findByCriadorId(Long usuarioId);
}
