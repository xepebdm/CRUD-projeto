package br.com.danieladv.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.danieladv.model.Usuario;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioServiceTest {

	private Usuario user1;
	private Usuario user2;

	@Autowired
	private UsuarioService service;

	
	@Before
	public void setUp() {
		user1 = new Usuario();
		user1.setNome("Heloísa");
		user1.setCpf("133.295.950-43");
		user1.setDataDeNascimento("11/03/2000");
		user1.setSexo("Feminino");
		user1.setEstadoCivil("Casado");
		user1.setEmail("heloisa@gmail.com");
		user1.setAtivo(true);
		
		user2 = new Usuario();
		user2.setNome("Maria Heloísa");
		user2.setCpf("465.946.830-55");
		user2.setDataDeNascimento("18/12/2013");
		user2.setSexo("Feminino");
		user2.setEstadoCivil("Casado");
		user2.setEmail("heloisa@gmail.com");
		user2.setAtivo(true);
		
	}
	
	@Test
	public void testAdicionaUsuario() {
		service.save(user1);
		
		Usuario usuario = service.findByNome(user1.getNome());
		
		assertEquals(usuario.getNome(), user1.getNome());
	}
	
	@Test
	public void testProcuraPorNome() {
		service.save(user1);
		Usuario usuario = service.findByNome("heloísa");
		
		assertEquals("CASADO", usuario.getEstadoCivil());
	}
	
	@Test
	public void testProcuraPorCpf() {
		service.save(user1);
		
		Usuario usuario = service.findByCPF("133.295.950-43");
		
		assertEquals("FEMININO", usuario.getSexo());
	}
	
	@Test
	public void testAtualizaUsuario() {
		service.save(user1);
		
		Usuario usuario = service.findByNome("heloísa");
		
		usuario.setAtivo(false);
		service.update(usuario);
		
		Usuario user2 = service.findByNome("heloísa");
		
		assertFalse(user2.isAtivo());
	}
	
	@Test(expected = Exception.class)
	public void testRemoveUsuario() {
		service.save(user1);
		
		Usuario usuario = service.findByNome("HELOÍSA");
		
		service.delete(usuario);
		
		service.findByNome("heloísa");
	}
	
	
	@Test
	public void testProcuraListaDeUsuarioPorNome() {
		service.save(user1);
		service.save(user2);
		
		List<Usuario> list = service.findAllByNome("heloísa");
		
		assertEquals(2, list.size());
	}
	
	@After
	public void rollback() {
		service.deleteAll();
	}

}
