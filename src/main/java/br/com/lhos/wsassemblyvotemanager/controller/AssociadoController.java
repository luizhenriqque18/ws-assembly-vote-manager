package br.com.lhos.wsassemblyvotemanager.controller;

import br.com.lhos.wsassemblyvotemanager.domain.Associado;
import br.com.lhos.wsassemblyvotemanager.dto.AssociadoDTO;
import br.com.lhos.wsassemblyvotemanager.exception.AssociadoNaoExisteEx;
import br.com.lhos.wsassemblyvotemanager.service.AssociadoService;
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
@RequestMapping("/associado")
public class AssociadoController {

    AssociadoService associadoService;

    public AssociadoController(AssociadoService associadoService) {
        this.associadoService = associadoService;
    }

    @PostMapping()
    public ResponseEntity<AssociadoDTO> create(@Valid @RequestBody AssociadoDTO associadoDTO) {
        Associado associado = associadoDTO.convertDTOToEntity();

        return ResponseEntity.status(HttpStatus.CREATED).body(associadoService.save(associado).convertEntityToDTO());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssociadoDTO> update(@PathVariable("id") UUID id, @RequestBody @Valid AssociadoDTO associadoDTO) throws AssociadoNaoExisteEx {
        associadoDTO.setAssociadoId(id);
        associadoService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(associadoService.save(associadoDTO.convertDTOToEntity()).convertEntityToDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssociadoDTO> findById(@PathVariable(value = "id") UUID id) throws AssociadoNaoExisteEx {
        Associado associado = associadoService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(associado.convertEntityToDTO());
    }

    @GetMapping()
    public ResponseEntity<Page<Associado>> findAll(@PageableDefault(page = 0, size = 10, sort = "associadoId", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(associadoService.findAll(pageable));
    }

}
