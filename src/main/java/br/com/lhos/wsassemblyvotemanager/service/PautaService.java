package br.com.lhos.wsassemblyvotemanager.service;

import br.com.lhos.wsassemblyvotemanager.domain.Pauta;
import br.com.lhos.wsassemblyvotemanager.exception.PautaNaoExisteEx;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PautaService {
    Pauta save(Pauta pauta);
    Pauta findById(UUID id) throws PautaNaoExisteEx;
    Page<Pauta> findAll(Pageable pageable);
}
