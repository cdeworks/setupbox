package br.com.astar.setupbox.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.astar.setupbox.domain.enums.TipoArquivoImportacao;
import br.com.astar.setupbox.domain.model.Parametro;

@Repository
public interface ParametroRepository extends JpaRepository<Parametro, Long> {

	List<Parametro> findByTipo(TipoArquivoImportacao tipo);

}
