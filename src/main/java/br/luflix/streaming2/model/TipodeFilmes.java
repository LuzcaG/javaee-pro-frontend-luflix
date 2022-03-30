package br.luflix.streaming2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
public class TipodeFilmes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	private String nome;
	@NotEmpty
	private String genero;
	@NotEmpty
	private String sinopse; 
	@NotEmpty
	private String palavraChave;
	@NotEmpty
	private String DataDeLancamento;
	@NotEmpty
	private String Diretor;
	
}
