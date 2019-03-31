package br.com.danieladv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.danieladv.model.Usuario;
import br.com.danieladv.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public List<Usuario> getAll() {
		
		return repository.findAll();
	}

	public void save(Usuario usuario) {
		repository.save(usuario);
	}

	public List<Usuario> findAllByNome(String nome) {
		return (List<Usuario>) repository.findAllByNome(nome);
	}
	
	public Usuario findByNome(String nome) {
		Usuario user = repository.findByNome(nome).get();
		
		if(user == null) {
			throw new IllegalArgumentException("Não foi encontrado Usuário com o nome informado!");
		}
		
		return user;
	}

	public Usuario findByCPF(String cpf) {
		return repository.findByCpf(cpf).get();
	}
	
	public void update(Usuario usuario) {
		repository.save(usuario);
	}
	
	public void delete(Usuario usuario) {
		repository.delete(usuario);
	}
	
	public void deleteAll() {
		repository.deleteAll();
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public Usuario findById(Long id) {
		Usuario usuario; 
		
		if(id == null) {
			usuario = new Usuario();
			usuario.setSexo("MASCULINO");
			usuario.setEstadoCivil("SOLTEIRO");
		}else {
			usuario = repository.findById(id).get();
		}
		
		
		return usuario; 
	}
	
	
}
