package com.davi.encontro_esportes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "partidas")
@Getter
@Setter
@NoArgsConstructor
public class PartidaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "criador_id")
    private UsuarioEntity criador;

    @ManyToOne(optional = false)
    @JoinColumn(name = "esporte_id")
    private EsporteEntity esporte;

    @Column(nullable = false)
    private String local;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(nullable = false)
    private Integer maxParticipantes;

    private String descricao;

    @Column(nullable = false)
    private LocalDateTime criadoEm;
}
