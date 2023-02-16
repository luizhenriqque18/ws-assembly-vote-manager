package br.com.lhos.wsassemblyvotemanager.controller;

import br.com.lhos.wsassemblyvotemanager.domain.Pauta;
import br.com.lhos.wsassemblyvotemanager.dto.PautaDTO;
import br.com.lhos.wsassemblyvotemanager.exception.PautaNaoExisteEx;
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
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/pauta")
public class PautaController{
    PautaService pautaService;
    SessaoVotacaoService sessaoVotacaoService;

    public PautaController(PautaService pautaService, SessaoVotacaoService sessaoVotacaoService) {
        this.pautaService = pautaService;
        this.sessaoVotacaoService = sessaoVotacaoService;
    }

    @PostMapping()
    public ResponseEntity<PautaDTO> create(@Valid @RequestBody PautaDTO pautaDTO) throws SessaoVotacaoNaoExisteEx {
        sessaoVotacaoService.findById(pautaDTO.getSessaoVotacaoId());
        Pauta pauta = pautaDTO.convertDTOToEntity();

        return ResponseEntity.status(HttpStatus.CREATED).body(pautaService.save(pauta).convertEntityToDTO());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PautaDTO> update(@PathVariable("id") UUID id, @RequestBody @Valid PautaDTO pautaDTO) throws PautaNaoExisteEx, SessaoVotacaoNaoExisteEx {

        pautaService.findById(id);
        sessaoVotacaoService.findById(pautaDTO.getSessaoVotacaoId());
        pautaDTO.setPautaId(id);
        Pauta pauta = pautaDTO.convertDTOToEntity();
        return ResponseEntity.status(HttpStatus.OK).body(pautaService.save(pauta).convertEntityToDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id) throws PautaNaoExisteEx {
        Pauta pauta = pautaService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(pauta);
    }

    @GetMapping()
    public ResponseEntity<Page<Pauta>> findAll(@PageableDefault(page = 0, size = 10, sort = "pautaId", direction = Sort.Direction.ASC) Pageable pageable){
        Page<Pauta> pautaes = pautaService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(pautaes);
    }
}
