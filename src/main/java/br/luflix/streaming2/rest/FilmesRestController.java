package br.luflix.streaming2.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.luflix.streaming2.model.TipodeFilmes;
import br.luflix.streaming2.repository.FilmesRepository;

@RequestMapping("/api/filmes")
@RestController
public class FilmesRestController {
	@Autowired
	private FilmesRepository filmRep;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Iterable<TipodeFilmes> getFilmes(){
		return filmRep.findAll();
	}
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.GET)
	public ResponseEntity<TipodeFilmes> findOneFilm(@PathVariable("id") Long id){
		Optional<TipodeFilmes> filme = filmRep.findById(id);
		if (filme.isPresent()) {
			return ResponseEntity.ok(filme.get());
			
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@RequestMapping(value = "/tipo/{id}", method = RequestMethod.GET)
	public Iterable<TipodeFilmes> getfilmeByTipo(@PathVariable("id") Long idTipo){



	return filmRep.findByGeneroId(idTipo);



	}
	@RequestMapping(value = "/diretor/{diretor}", method = RequestMethod.GET)
	public Iterable<TipodeFilmes> getfilmeByTipo(@PathVariable("diretor") String diretor){



	return filmRep.findByDiretor(diretor);



	}
	

}
