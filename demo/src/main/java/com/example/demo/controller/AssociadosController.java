package com.example.demo.controller;

import com.example.demo.service.AssociadosService;
import com.example.demo.vo.AssociadosVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/associados")
@RequiredArgsConstructor
public class AssociadosController {

    private final AssociadosService associadosService;

    @PostMapping
    public ResponseEntity<AssociadosVo> createAssociados(@RequestBody @Valid AssociadosVo associadosVo) {
        return ResponseEntity.ok(associadosService.createAssociado(associadosVo));
    }

    @GetMapping
    public ResponseEntity<List<AssociadosVo>> listarAssociados() {
        return ResponseEntity.ok(associadosService.listarAssociados());
    }

}
