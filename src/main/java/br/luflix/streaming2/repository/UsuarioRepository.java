package br.luflix.streaming2.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.luflix.streaming2.model.Usuarios;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuarios, Long>{

}
