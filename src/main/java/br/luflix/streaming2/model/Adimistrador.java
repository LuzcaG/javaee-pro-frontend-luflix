package br.luflix.streaming2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import br.luflix.streaming2.util.HashUtil;
import lombok.Data;
//para gerar como uma entiadade JPA

@Data
//Para mapear 
@Entity
public class Adimistrador {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	private String nome;
	@Email
	@Column(unique = true)
	private String email;
	@NotEmpty
	private String senha;
	
	//meodo para setar a senha aplicando hash  
	public void setSenha(String senha) {
		//
		this.senha = HashUtil.hash256(senha); 
	}
	public void setSenhaComHash(String hash) {
		this.senha = hash;
	}
}
