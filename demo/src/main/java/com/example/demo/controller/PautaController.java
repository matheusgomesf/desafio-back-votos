package com.example.demo.controller;

import com.example.demo.service.PautaService;
import com.example.demo.vo.PautaVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("pauta")
@RequiredArgsConstructor
public class PautaController {

    private final PautaService pautaService;

    @PostMapping
    public ResponseEntity<PautaVO> criarPauta(@RequestBody @Valid PautaVO pautaVO) {
        return ResponseEntity.ok(pautaService.criarPauta(pautaVO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> resultadoPauta(@PathVariable Long id) {
        return ResponseEntity.ok(pautaService.resultadoPauta(id));
    }

}
