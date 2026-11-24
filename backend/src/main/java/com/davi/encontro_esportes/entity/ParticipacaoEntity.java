package com.davi.encontro_esportes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "participacoes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"partida_id", "usuario_id"})
)
@Getter
@Setter
@NoArgsConstructor
public class ParticipacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "partida_id")
    private PartidaEntity partida;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @Column(nullable = false)
    private LocalDateTime entrouEm;
}
