package com.davi.encontro_esportes.repository;

import com.davi.encontro_esportes.entity.Genero;
import com.davi.encontro_esportes.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    boolean existsByEmail(String email);

    @Query("""
            SELECT u FROM UsuarioEntity u\s
                       WHERE (:nome IS NULL OR LOWER(u.nome) LIKE LOWER(CONCAT('%', :nome, '%')))
                                  AND (:genero IS NULL OR u.genero = :genero)
                                  AND (:nascidoAte IS NULL OR u.dataNascimento <= :nascidoAte)
                                  AND (:nascidoDepois IS NULL OR u.dataNascimento > :nascidoDepois)
                                  AND (:localizacao IS NULL OR LOWER(u.localizacao) LIKE LOWER(CONCAT('%', :localizacao, '%')))
                                  AND (:usuarioId IS NULL OR :usuarioId <> u.id)
                                  AND (:esporteId IS NULL OR EXISTS (
                                              SELECT ue FROM UsuarioEsporteEntity ue 
                                              WHERE ue.usuario = u AND ue.esporte.id = :esporteId))
            """)
    List<UsuarioEntity> buscar(@Param("nome") String nome,
                               @Param("genero") Genero genero,
                               @Param("nascidoAte") LocalDate idadeMin,
                               @Param("nascidoDepois") LocalDate idadeMax,
                               @Param("localizacao") String localizacao,
                               @Param("usuarioId") Long usuarioId,
                               @Param("esporteId") Long esporteId);
}
