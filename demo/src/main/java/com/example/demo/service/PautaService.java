package com.example.demo.service;

import com.example.demo.enums.StatusUser;
import com.example.demo.enums.TipoVoto;
import com.example.demo.model.SessaoVotacao;
import com.example.demo.vo.VotoVO;
import com.example.demo.exception.PautaException;
import com.example.demo.model.Pauta;
import com.example.demo.repository.PautaRepository;
import com.example.demo.vo.AssociadosVo;
import com.example.demo.vo.PautaVO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PautaService {

    private final ModelMapper modelMapper = new ModelMapper();
    private final PautaRepository pautaRepository;
    private final AssociadosService associadosService;

    public PautaVO criarPauta(PautaVO pautaVo) {
        Pauta pauta = pautaRepository.save(modelMapper.map(pautaVo, Pauta.class));
        return modelMapper.map(pauta, PautaVO.class);
    }

    public PautaVO findById(Long id) {
        Pauta pauta = pautaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pauta não encontrada"));
        return modelMapper.map(pauta, PautaVO.class);
    }

    public VotoVO votarPauta(VotoVO votoVO) {
        PautaVO pautaVO = this.findById(votoVO.getPauta());

        validarSessaoPauta(pautaVO.getSessaoVotacao());

        validarVotosPauta(pautaVO.getVotos(), votoVO.getCpf());

        contabilizarVotos(pautaVO, votoVO.getVoto());

        pautaRepository.save(modelMapper.map(pautaVO, Pauta.class));

        return votoVO;
    }

    private void contabilizarVotos(PautaVO pautaVO, String voto) {
        if (TipoVoto.SIM.getTipoVoto().equals(voto)) {
            pautaVO.setVotoSim(pautaVO.getVotoSim()+1);
        } else if (TipoVoto.NAO.getTipoVoto().equals(voto)){
            pautaVO.setVotoNao(pautaVO.getVotoNao()+1);
        } else {
            throw new PautaException("Voto invalido");
        }
    }

    private void validarVotosPauta(List<AssociadosVo> votos, String cpfAssociado) {
        if (Objects.nonNull(votos) &&
                !votos.isEmpty() &&
                votos.stream().map(AssociadosVo::getCpf).anyMatch(cpf -> cpf.equals(cpfAssociado))) {
            throw new PautaException("Associado já votou");
        } else {
            AssociadosVo associadosVo = associadosService.findByCpf(cpfAssociado);
            validarAssociado(associadosVo);
            votos.add(associadosVo);
        }
    }

    private void validarAssociado(AssociadosVo associadosVo) {
        StatusUser statusUser = associadosService.validarCpfAssociado(associadosVo);
        if (StatusUser.UNABLE_TO_VOTE.getStatus().equals(statusUser.getStatus())) {
            throw new PautaException("Associado não pode votar");
        }
    }

    private void validarSessaoPauta(SessaoVotacao sessaoVotacao) {
        if (Objects.isNull(sessaoVotacao)) {
            throw new PautaException("Pauta não possui sessão de votação");
        }

        if (!sessaoVotacao.getHoraFim().isAfter(LocalDateTime.now())) {
            throw new PautaException("Sessão da pauta finalizada");
        }
    }

    public String resultadoPauta(Long id) {
        PautaVO pautaVO = this.findById(id);
        return pautaVO.getVotoSim() > pautaVO.getVotoNao() ? TipoVoto.SIM.getTipoVoto() : TipoVoto.NAO.getTipoVoto();
    }

}
