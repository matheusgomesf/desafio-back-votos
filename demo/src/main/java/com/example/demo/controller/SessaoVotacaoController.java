package com.example.demo.controller;

import com.example.demo.service.SessaoVotacaoService;
import com.example.demo.vo.SessaoVotacaoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("sessaovotacao")
@RequiredArgsConstructor
public class SessaoVotacaoController {

    private final SessaoVotacaoService sessaoVotacaoService;

    @PostMapping
    public ResponseEntity<SessaoVotacaoVO> criarSessaoVotacao(@RequestBody @Valid SessaoVotacaoVO sessaoVotacaoVO) {
        return ResponseEntity.ok(sessaoVotacaoService.criarSessaoVotacao(sessaoVotacaoVO));
    }
}
