package com.example.demo.service;

import com.example.demo.enums.StatusUser;
import com.example.demo.exception.PautaException;
import com.example.demo.model.Associados;
import com.example.demo.repository.AssociadosRepository;
import com.example.demo.vo.AssociadosVo;
import com.example.demo.vo.UserInfoVo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssociadosService {

    private final ModelMapper modelMapper = new ModelMapper();
    private final AssociadosRepository associadosRepository;

    @Value("${url.service.cpf}")
    private String URL_USER_INFO;

    public AssociadosVo findById(Long id) {
        Associados associado = associadosRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Associado não encontrado"));

        return modelMapper.map(associado, AssociadosVo.class);
    }

    public AssociadosVo findByCpf(String cpf) {
        Associados associado = associadosRepository.findByCpf(cpf)
                .orElseThrow(() -> new EntityNotFoundException("Associado não encontrado"));

        return modelMapper.map(associado, AssociadosVo.class);
    }

    public AssociadosVo createAssociado(AssociadosVo associadosVo) {
        Associados save = associadosRepository.save(modelMapper.map(associadosVo, Associados.class));
        return associadosVo;
    }

    public StatusUser validarCpfAssociado(AssociadosVo associadosVo) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<UserInfoVo> userStatus = restTemplate.getForEntity(URL_USER_INFO + associadosVo.getCpf(), UserInfoVo.class);
            return StatusUser.valueOf(userStatus.getBody().getStatus());
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new PautaException("Associado com cpf invalido");
            } else {
                e.printStackTrace();
                throw new PautaException("Erro ao validar CPF");
            }
        }
    }

    public List<AssociadosVo> listarAssociados() {
        return associadosRepository.findAll().stream()
                .map(associados -> modelMapper.map(associados, AssociadosVo.class))
                .collect(Collectors.toList());
    }
}
