package br.com.danieladv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.danieladv.model.Usuario;
import br.com.danieladv.service.UsuarioService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CadastroControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private UsuarioService service;
	
	@Before
	public void setUp() throws Exception{
		
		Usuario user1 = new Usuario();
		user1.setNome("Heloísa");
		user1.setCpf("133.295.950-43");
		user1.setDataDeNascimento("11/03/2000");
		user1.setSexo("Feminino");
		user1.setEstadoCivil("Casado");
		user1.setEmail("heloisa@gmail.com");
		user1.setAtivo(true);
		
		Usuario user2 = new Usuario();
		user2.setNome("Maria Heloísa");
		user2.setCpf("465.946.830-55");
		user2.setDataDeNascimento("18/12/2013");
		user2.setSexo("Feminino");
		user2.setEstadoCivil("Casado");
		user2.setEmail("heloisa@gmail.com");
		user2.setAtivo(true);
		
		
		service.save(user1);
		service.save(user2);
	}
	
	@Test
	public void testURLPrincipal() throws Exception {
		mock.perform(get("/"))
		.andExpect(model().attributeExists("usuarios"))
		.andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void testURLPrincipalComVerboPost() throws Exception{
		mock.perform(post("/")).andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void testUrlPesquisarPorNome() throws Exception{
		mock.perform(get("/pesquisar")
				.param("type", "nome")
				.param("value", "heloisa"))
		.andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void testUrlPesquisarPorCpf() throws Exception{
		mock.perform(get("/pesquisar")
				.param("type", "cpf")
				.param("value", "133.295.950-43"))
		.andExpect(status().is2xxSuccessful());
	}
	
	@Test(expected = Exception.class)
	public void testUrlPesquisarPorCPfInvalido() throws Exception{
		mock.perform(get("/pesquisar")
				.param("type", "cpf")
				.param("value", "124.306.061-54"))
		.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void testUrlDeletarUsuarioPorId() throws Exception{
		Usuario usuario = service.findAllByNome("heloisa").get(0);
		Long id = usuario.getId();
		
		mock.perform(get("/delete").param("id", String.valueOf(id)))
		.andExpect(status().is3xxRedirection());
	}
	
	@After
	public void rollback() throws Exception{
		service.deleteAll();
	}

}
