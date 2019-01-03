package br.com.astar.setupbox.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.astar.setupbox.domain.model.GigasBancada;

@Repository
public interface GigasBancadaRepository extends JpaRepository<GigasBancada, Integer> {

	GigasBancada findByDiagnosticoAndGigas(String tipoDefeito, String gigas);

}
