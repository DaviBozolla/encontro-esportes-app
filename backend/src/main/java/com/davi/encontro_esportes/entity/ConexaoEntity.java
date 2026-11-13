package com.davi.encontro_esportes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "conexoes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"seguidor_id", "seguido_id"})
)
@Getter
@Setter
@NoArgsConstructor
public class ConexaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name= "seguidor_id")
    private UsuarioEntity seguidor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "seguido_id")
    private UsuarioEntity seguido;

    @Column(nullable = false)
    private LocalDateTime criadoEm;

}
