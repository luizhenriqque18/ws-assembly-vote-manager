package br.com.lhos.wsassemblyvotemanager.controller;

import br.com.lhos.wsassemblyvotemanager.domain.Pauta;
import br.com.lhos.wsassemblyvotemanager.domain.SessaoVotacao;
import br.com.lhos.wsassemblyvotemanager.domain.Voto;
import br.com.lhos.wsassemblyvotemanager.dto.VotoDTO;
import br.com.lhos.wsassemblyvotemanager.enumeration.SessaoVotacaoStatusEnum;
import br.com.lhos.wsassemblyvotemanager.enumeration.VotoSituacaoEnum;
import br.com.lhos.wsassemblyvotemanager.exception.AssociadoNaoExisteEx;
import br.com.lhos.wsassemblyvotemanager.exception.PautaNaoExisteEx;
import br.com.lhos.wsassemblyvotemanager.exception.VotoEncerradoEx;
import br.com.lhos.wsassemblyvotemanager.exception.VotoJaRegistradoEx;
import br.com.lhos.wsassemblyvotemanager.service.AssociadoService;
import br.com.lhos.wsassemblyvotemanager.service.PautaService;
import br.com.lhos.wsassemblyvotemanager.service.SessaoVotacaoService;
import br.com.lhos.wsassemblyvotemanager.service.VotoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/voto")
public class VotoController {

    VotoService votoService;
    PautaService pautaService;
    SessaoVotacaoService sessaoVotacaoService;
    AssociadoService associadoService;

    public VotoController(VotoService votoService, PautaService pautaService, SessaoVotacaoService sessaoVotacaoService, AssociadoService associadoService) {
        this.votoService = votoService;
        this.pautaService = pautaService;
        this.sessaoVotacaoService = sessaoVotacaoService;
        this.associadoService = associadoService;
    }

    @PostMapping()
    public ResponseEntity<VotoDTO> create(@Valid @RequestBody VotoDTO votoDTO)
            throws PautaNaoExisteEx, AssociadoNaoExisteEx, VotoEncerradoEx, VotoJaRegistradoEx {
        Voto voto = votoDTO.convertDTOToEntity();

        Pauta pauta =  pautaService.findById(votoDTO.getPautaId());
        associadoService.findById(votoDTO.getAssociadoId());

        votoService.existAssociado(votoDTO.getAssociadoId());

        SessaoVotacao sessaoVotacao = pauta.getSessaoVotacao();
        if (sessaoVotacao.getStatus() == SessaoVotacaoStatusEnum.CLOSED ||
                sessaoVotacao.getStatus() == SessaoVotacaoStatusEnum.CANCELED) {
            throw new VotoEncerradoEx("Votação já encerada");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(votoService.save(voto).convertEntityToDTO());
    }
}
