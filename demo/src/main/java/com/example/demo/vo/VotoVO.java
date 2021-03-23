package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VotoVO {

    @NotBlank(message = "O cpf do associado não pode estar vazio")
    private String cpf;
    @NotBlank(message = "O voto da pauta não pode estar vazio")
    private String voto;
    @NotNull(message = "A pauta não pode estar vazia")
    private Long pauta;
}
