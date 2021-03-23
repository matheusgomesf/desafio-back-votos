package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssociadosVo {

    private Long id;
    @NotBlank(message = "O CPF não pode ser vazio")
    private String cpf;

    public AssociadosVo(String cpf) {
        this.cpf = cpf;
    }
}
