package com.davi.encontro_esportes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(
        name = "usuario_esportes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "esporte_id"})
)
@Getter
@Setter
@NoArgsConstructor
public class UsuarioEsporteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "esporte_id")
    private EsporteEntity esporte;

    @Enumerated(EnumType.STRING)
    @Column (nullable = false)
    private NivelEsporte nivel;

    private LocalDate praticaDesde;
}
