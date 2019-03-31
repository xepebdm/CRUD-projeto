package br.com.danieladv.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.br.CPF;

@Entity(name = "USUARIO")
public class Usuario {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	
	@CPF
	private String cpf;
	
	private String email;
	private String dataDeNascimento;
	private Sexo sexo;
	private EstadoCivil estadoCivil;
	private boolean ativo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSexo() {
		return sexo.name();
	}

	public void setSexo(String sexo) {
		this.sexo = Sexo.valueOf(sexo.toUpperCase());
	}

	public String getEstadoCivil() {
		return estadoCivil.name();
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = EstadoCivil.valueOf(estadoCivil.toUpperCase());
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public Long getId() {
		return id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
