package br.luflix.streaming2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
@Data
@Entity
public class TipodeGenero {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	private String nome;
	@NotEmpty
	@Column(columnDefinition = "text")
	private String descricao;
	@NotEmpty
	private String palavraChave;


}
