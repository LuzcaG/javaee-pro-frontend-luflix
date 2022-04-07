package br.luflix.streaming2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
public class TipodeFilmes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//@NotEmpty
	private String nome;
	@ManyToOne
	private TipodeGenero genero;
	//@NotEmpty
	private String sinopse; 
	
	private String palavraChave;
	//@NotEmpty
	private String dataDeLancamento;
	//@NotEmpty
	private String diretor;
	
	@Column(columnDefinition = "TEXT")
	private String fotos;
	
}
