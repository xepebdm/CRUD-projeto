package br.com.danieladv.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.danieladv.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findByNome(String nome);

	Optional<Usuario> findByCpf(String cpf);
	
	@Query("SELECT u FROM USUARIO u WHERE u.nome like %:nome% ")
	Collection<Usuario> findAllByNome(@Param("nome") String nome);
}
