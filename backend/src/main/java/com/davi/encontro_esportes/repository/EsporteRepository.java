package com.davi.encontro_esportes.repository;

import com.davi.encontro_esportes.entity.EsporteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EsporteRepository extends JpaRepository<EsporteEntity, Long> {
}
