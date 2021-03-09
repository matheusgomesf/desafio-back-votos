package com.example.demo.vo;

import com.example.demo.model.Pauta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessaoVotacaoVO {
    private Long id;
    @NotNull(message = "A pauta não pode estar vazio")
    private PautaVO pauta;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFim;
    private Integer duracao;
}
