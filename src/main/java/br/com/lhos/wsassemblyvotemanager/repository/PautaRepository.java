package br.com.lhos.wsassemblyvotemanager.repository;

import br.com.lhos.wsassemblyvotemanager.domain.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PautaRepository extends JpaRepository<Pauta, UUID> {

    public Optional<Pauta> findByPautaId(UUID uuid);
}
