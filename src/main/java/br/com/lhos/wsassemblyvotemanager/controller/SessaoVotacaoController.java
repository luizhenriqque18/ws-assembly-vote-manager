package br.com.lhos.wsassemblyvotemanager.controller;

import br.com.lhos.wsassemblyvotemanager.domain.Pauta;
import br.com.lhos.wsassemblyvotemanager.domain.SessaoVotacao;
import br.com.lhos.wsassemblyvotemanager.dto.SessaoVotacaoDTO;
import br.com.lhos.wsassemblyvotemanager.enumeration.SessaoVotacaoStatusEnum;
import br.com.lhos.wsassemblyvotemanager.exception.PautaNaoExisteEx;
import br.com.lhos.wsassemblyvotemanager.exception.SessaoVotacaoEmProgressoEx;
import br.com.lhos.wsassemblyvotemanager.exception.SessaoVotacaoNaoExisteEx;
import br.com.lhos.wsassemblyvotemanager.service.PautaService;
import br.com.lhos.wsassemblyvotemanager.service.SessaoVotacaoService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/sessao-votacao")
public class SessaoVotacaoController {

    SessaoVotacaoService sessaoVotacaoService;

    PautaService pautaService;

    public SessaoVotacaoController(SessaoVotacaoService sessaoVotacaoService, PautaService pautaService) {
        this.sessaoVotacaoService = sessaoVotacaoService;
        this.pautaService = pautaService;
    }

    @PostMapping()
    public ResponseEntity<SessaoVotacaoDTO> create(@Valid @RequestBody SessaoVotacaoDTO sessaoVotacaoDTO)
            throws SessaoVotacaoNaoExisteEx {

        Optional<Pauta> pauta = pautaService.findById(sessaoVotacaoDTO.getPautaId());

        if(!pauta.isPresent()) {
            throw new SessaoVotacaoNaoExisteEx("Pauta id " + sessaoVotacaoDTO.getSessaoVotacaoId() + " não existe.");
        }

        SessaoVotacao sessaoVotacao = sessaoVotacaoDTO.convertDTOToEntity();
        sessaoVotacao.setStatus(SessaoVotacaoStatusEnum.PENDING);

        return ResponseEntity.status(HttpStatus.CREATED).body(sessaoVotacaoService.save(sessaoVotacao).convertEntityToDTO());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") UUID id, @Valid @RequestBody SessaoVotacaoDTO sessaoVotacaoDTO)
            throws SessaoVotacaoNaoExisteEx, PautaNaoExisteEx, SessaoVotacaoEmProgressoEx {
        sessaoVotacaoDTO.setSessaoVotacaoId(id);

        Optional<Pauta> pauta = pautaService.findById(sessaoVotacaoDTO.getPautaId());

        if(!pauta.isPresent()) {
            throw new PautaNaoExisteEx("Pauta id " + sessaoVotacaoDTO.getSessaoVotacaoId() + " não existe.");
        }

        SessaoVotacao findResult = sessaoVotacaoService.findById(sessaoVotacaoDTO.getSessaoVotacaoId());

        SessaoVotacao sessaoVotacao = sessaoVotacaoDTO.convertDTOToEntity();

        if(findResult.getStatus() == SessaoVotacaoStatusEnum.PENDING) {
            if(sessaoVotacao.getStatus() == SessaoVotacaoStatusEnum.OPEN) {
                sessaoVotacao.setInicioSessao(LocalDateTime.now());
                sessaoVotacao.setStatus(SessaoVotacaoStatusEnum.OPEN);
            }
        } else {
            BeanUtils.copyProperties(findResult, sessaoVotacao);
        }

        if(findResult.getStatus() == SessaoVotacaoStatusEnum.OPEN) {

            if(sessaoVotacao.getStatus() == SessaoVotacaoStatusEnum.CANCELED) {
                sessaoVotacao.setStatus(SessaoVotacaoStatusEnum.CANCELED);
            }

            if(sessaoVotacao.getStatus() == SessaoVotacaoStatusEnum.CLOSED) {
                sessaoVotacao.setStatus(SessaoVotacaoStatusEnum.CLOSED);
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(sessaoVotacaoService.save(sessaoVotacao).convertEntityToDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id) throws SessaoVotacaoNaoExisteEx {
        SessaoVotacao sessaoVotacao = sessaoVotacaoService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(sessaoVotacao);
    }

    @GetMapping()
    public ResponseEntity<Page<SessaoVotacao>> findAll(@PageableDefault(page = 0, size = 10, sort = "sessaoVotacaoId", direction = Sort.Direction.ASC) Pageable pageable){
        Page<SessaoVotacao> sessaoVotacaoes = sessaoVotacaoService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(sessaoVotacaoes);
    }
}
