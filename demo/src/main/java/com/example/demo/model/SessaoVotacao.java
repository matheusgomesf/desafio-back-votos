package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessaoVotacao implements Serializable {
    private static final long serialVersionUID = -4948037873024740857L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "pauta", referencedColumnName = "id")
    @NotNull(message = "A pauta não pode estar vazio")
    private Pauta pauta;
    @NotNull(message = "A horaInicio não pode estar vazio")
    private LocalDateTime horaInicio;
    @NotNull(message = "A horaFim não pode estar vazio")
    private LocalDateTime horaFim;
    @NotNull(message = "A duracao não pode estar vazio")
    private Integer duracao;

}
