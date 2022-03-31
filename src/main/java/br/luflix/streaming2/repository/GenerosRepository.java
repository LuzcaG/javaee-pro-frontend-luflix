package br.luflix.streaming2.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.luflix.streaming2.model.TipodeGenero;

public interface GenerosRepository extends PagingAndSortingRepository<TipodeGenero, Long>{

}
