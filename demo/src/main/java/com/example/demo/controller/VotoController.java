package com.example.demo.controller;

import com.example.demo.service.PautaService;
import com.example.demo.vo.VotoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/voto")
@RequiredArgsConstructor
public class VotoController {

    private final PautaService pautaService;

    @PostMapping
    public ResponseEntity<VotoVO> votarPauta(@RequestBody @Valid VotoVO votoVO) {
        return ResponseEntity.ok(pautaService.votarPauta(votoVO));
    }
}
