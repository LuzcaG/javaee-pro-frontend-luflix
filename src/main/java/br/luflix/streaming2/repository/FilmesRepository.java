package br.luflix.streaming2.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.luflix.streaming2.model.TipodeFilmes;

public interface FilmesRepository extends PagingAndSortingRepository<TipodeFilmes, Long>{

}
