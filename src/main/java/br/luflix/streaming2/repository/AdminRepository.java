package br.luflix.streaming2.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.luflix.streaming2.model.Adimistrador;

public interface AdminRepository extends PagingAndSortingRepository<Adimistrador, Long>{
	

}
