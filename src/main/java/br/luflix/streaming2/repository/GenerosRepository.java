package br.luflix.streaming2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.luflix.streaming2.model.TipodeGenero;


public interface GenerosRepository extends PagingAndSortingRepository<TipodeGenero, Long>{
	
	@Query("SELECT tp FROM TipodeGenero tp WHERE tp.palavraChave LIKE %:tp%")
	public List<TipodeGenero> verGenero(@Param("tp") String Parametro);
	
	public List<TipodeGenero> findAllByOrderByNomeAsc();
}
