package br.com.lhos.wsassemblyvotemanager.repository;

import br.com.lhos.wsassemblyvotemanager.domain.Associado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssociadoRepository extends JpaRepository<Associado, UUID> {
}
