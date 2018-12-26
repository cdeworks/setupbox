package br.com.astar.setupbox.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.astar.setupbox.domain.model.Ativo;

public interface AtivoRepository extends JpaRepository<Ativo, Long> {

}
