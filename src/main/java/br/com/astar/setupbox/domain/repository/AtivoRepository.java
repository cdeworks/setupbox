package br.com.astar.setupbox.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.astar.setupbox.domain.model.Ativo;

@Repository
public interface AtivoRepository extends JpaRepository<Ativo, Long> {
	
	Optional<Ativo> findBySerialNumber(String serialNumber);

}
