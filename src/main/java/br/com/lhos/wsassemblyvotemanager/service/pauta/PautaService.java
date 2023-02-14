package br.com.lhos.wsassemblyvotemanager.service.pauta;

import br.com.lhos.wsassemblyvotemanager.domain.Pauta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface PautaService {
    Pauta save(Pauta pauta);
    Optional<Pauta> findById(UUID id);
    Page<Pauta> findAll(Pageable pageable);
}
