package com.example.demo.service;

import com.example.demo.exception.PautaException;
import com.example.demo.model.SessaoVotacao;
import com.example.demo.repository.SessaoVotacaoRepository;
import com.example.demo.vo.PautaVO;
import com.example.demo.vo.SessaoVotacaoVO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

        if (Objects.nonNull(pautaVO.getSessaoVotacao())) {
            throw new PautaException("Pauta já passui sessão");
        }

        sessaoVotacaoVO.setHoraFim(sessaoVotacaoVO.getHoraInicio().plusMinutes(sessaoVotacaoVO.getDuracao()));
        sessaoVotacaoVO.setPauta(pautaVO);
        sessaoVotacaoRepository.save(modelMapper.map(sessaoVotacaoVO, SessaoVotacao.class));

        return modelMapper.map(sessaoVotacaoVO, SessaoVotacaoVO.class);
    }
}
