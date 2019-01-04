package br.com.astar.setupbox.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.astar.setupbox.domain.model.DeParaGigasBancada;

@Repository
public interface GigasBancadaRepository extends JpaRepository<DeParaGigasBancada, Integer> {

	DeParaGigasBancada findByDiagnosticoAndGigas(String tipoDefeito, String gigas);

}
