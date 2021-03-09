package com.example.demo.service;

import com.example.demo.model.Pauta;
import com.example.demo.model.SessaoVotacao;
import com.example.demo.repository.SessaoVotacaoRepository;
import com.example.demo.vo.PautaVO;
import com.example.demo.vo.SessaoVotacaoVO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SessaoVotacaoService {

    private final ModelMapper modelMapper = new ModelMapper();
    private final SessaoVotacaoRepository sessaoVotacaoRepository;
    private final PautaService pautaService;

    public SessaoVotacaoVO criarSessaoVotacao(SessaoVotacaoVO sessaoVotacaoVO) {
        if (Objects.isNull(sessaoVotacaoVO.getHoraInicio())) {
            sessaoVotacaoVO.setHoraInicio(LocalDateTime.now());
        }

        if (Objects.isNull(sessaoVotacaoVO.getDuracao())) sessaoVotacaoVO.setDuracao(1);

        PautaVO pautaVO = pautaService.findById(sessaoVotacaoVO.getPauta().getId());

        sessaoVotacaoVO.setHoraFim(sessaoVotacaoVO.getHoraInicio().plusMinutes(sessaoVotacaoVO.getDuracao()));
        sessaoVotacaoVO.setPauta(pautaVO);
        SessaoVotacao sessaoVotacao = sessaoVotacaoRepository.save(modelMapper.map(sessaoVotacaoVO, SessaoVotacao.class));

        return modelMapper.map(sessaoVotacaoVO, SessaoVotacaoVO.class);
    }
}
