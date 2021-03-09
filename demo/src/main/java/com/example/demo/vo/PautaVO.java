package com.example.demo.vo;

import com.example.demo.enums.TipoVoto;
import com.example.demo.model.Associados;
import com.example.demo.model.SessaoVotacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PautaVO {
    private Long id;
    @NotBlank(message = "O assunto da pauta não pode estar vazio")
    private String assunto;
    private Integer votoSim;
    private Integer votoNao;
    private TipoVoto resultado;
    private List<Associados> votos;
    private SessaoVotacao sessaoVotacao;
}