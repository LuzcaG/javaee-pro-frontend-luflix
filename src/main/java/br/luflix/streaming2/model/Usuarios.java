package br.luflix.streaming2.model;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.luflix.streaming2.util.HashUtil;
import lombok.Data;

@Entity
@Data
public class Usuarios {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String senha;
	@Column(unique = true)
	private String email;
	
	public void setSenha(String senha) {
		this.senha = HashUtil.hash256(senha); 
	}
}
