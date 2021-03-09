package com.example.demo.service;

import com.example.demo.model.Pauta;
import com.example.demo.repository.PautaRepository;
import com.example.demo.vo.PautaVO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class PautaService {

    private final ModelMapper modelMapper = new ModelMapper();
    private final PautaRepository pautaRepository;

    public PautaVO criarPauta(PautaVO pautaVo) {
        Pauta pauta = pautaRepository.save(modelMapper.map(pautaVo, Pauta.class));
        return modelMapper.map(pauta, PautaVO.class);
    }

    public PautaVO findById(Long id) {
        Pauta pauta = pautaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pauta não encontrada"));
        return modelMapper.map(pauta, PautaVO.class);
    }
}
