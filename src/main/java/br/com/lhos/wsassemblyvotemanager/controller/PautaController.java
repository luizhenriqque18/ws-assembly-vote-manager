package br.com.lhos.wsassemblyvotemanager.controller;

import br.com.lhos.wsassemblyvotemanager.domain.Pauta;
import br.com.lhos.wsassemblyvotemanager.dto.PautaDTO;
import br.com.lhos.wsassemblyvotemanager.service.pauta.PautaService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/ws-assembly-vote-manager/pauta")
public class PautaController{

    PautaService pautaService;

    public PautaController(PautaService pautaService){
        this.pautaService = pautaService;
    }

    @PostMapping()
    public ResponseEntity<Object> create(@Valid @RequestBody PautaDTO pautaDTO) {
        Pauta pauta = new Pauta();
        BeanUtils.copyProperties(pautaDTO, pauta);
        return ResponseEntity.status(HttpStatus.CREATED).body(pautaService.save(pauta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") UUID id, @RequestBody @Valid PautaDTO pautaDTO) {
        Optional<Pauta> pauta = pautaService.findById(id);

        if(!pauta.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pauta id " + id + " não existe.");
        }

        BeanUtils.copyProperties(pautaDTO, pauta.get());
        return ResponseEntity.status(HttpStatus.OK).body(pautaService.save(pauta.get()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id) {
        Optional<Pauta> pauta = pautaService.findById(id);

        if(!pauta.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pauta id " + id + " não existe.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(pauta.get());
    }

    @GetMapping()
    public ResponseEntity<Page<Pauta>> findAll(@PageableDefault(page = 0, size = 10, sort = "pautaId", direction = Sort.Direction.ASC) Pageable pageable){
        Page<Pauta> pautaes = pautaService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(pautaes);
    }
}
