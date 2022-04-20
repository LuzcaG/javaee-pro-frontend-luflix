package br.luflix.streaming2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.luflix.streaming2.model.Usuarios;
import br.luflix.streaming2.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioRestController {
	@Autowired
	private UsuarioRepository userRep;

	@RequestMapping(value = "", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Usuarios> criarUsuario(Usuarios user) {
		return null;
	}
}
