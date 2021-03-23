package com.example.demo.model;

import com.example.demo.enums.TipoVoto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pauta implements Serializable {
    private static final long serialVersionUID = 3700469750251131754L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "O assunto da pauta não pode estar vazio")
    private String assunto;
    private Integer votoSim;
    private Integer votoNao;
    private TipoVoto resultado;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<Associados> votos;
    @OneToOne(mappedBy = "pauta", fetch = FetchType.LAZY)
    private SessaoVotacao sessaoVotacao;

    public TipoVoto getResultados() {
        return this.votoSim > this.votoNao ? TipoVoto.SIM : TipoVoto.NAO;
    }
}
