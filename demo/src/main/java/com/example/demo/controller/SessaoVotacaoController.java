package com.example.demo.controller;

import com.example.demo.service.SessaoVotacaoService;
import com.example.demo.vo.PautaVO;
import com.example.demo.vo.SessaoVotacaoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
