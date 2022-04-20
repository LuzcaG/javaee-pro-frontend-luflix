package br.luflix.streaming2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.luflix.streaming2.model.TipodeFilmes;
import br.luflix.streaming2.model.TipodeGenero;

public interface FilmesRepository extends PagingAndSortingRepository<TipodeFilmes, Long>{
	
	@Query("SELECT tp FROM TipodeFilmes tp WHERE tp.genero LIKE %:tp%")
	public List<TipodeFilmes> verGenero(@Param("tp") String Parametro);
	
	@Query("SELECT tp FROM TipodeFilmes tp WHERE tp.diretor LIKE %:tp%")
	public List<TipodeFilmes> verDiretor(@Param("tp") String Parametro);
	
	public List<TipodeFilmes> findByGeneroId(Long id);
	
	public List<TipodeFilmes> findByDiretor(String diretor);
	
}
