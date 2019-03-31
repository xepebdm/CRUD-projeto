package br.com.danieladv.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.danieladv.model.EstadoCivil;
import br.com.danieladv.model.Sexo;
import br.com.danieladv.model.Usuario;
import br.com.danieladv.service.UsuarioService;

@Controller
@RequestMapping("/")
public class CadastroController {
	
	
	@Autowired
	private UsuarioService service;

	@GetMapping
	public ModelAndView inicio() {
		ModelAndView model = new ModelAndView("home");
		
		List<Usuario> usuarios = service.getAll();
		
		model.addObject("usuarios", usuarios);
		
		return model;
	}
	
	@PostMapping
	public ModelAndView salvarUsuario(Usuario usuario, RedirectAttributes redirect) {
		ModelAndView model = new ModelAndView("redirect:/");
		
		service.save(usuario);
		
		redirect.addFlashAttribute("mensagem", "Usuário " + usuario.getNome() + " cadastrado com sucesso!");
		
		return model;
	}
	
	@GetMapping(path = "pesquisar")
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView pesquisarPorNome(@RequestParam String type, @RequestParam String value) {
		ModelAndView model = new ModelAndView("home");
		
		List<Usuario> usuario = new ArrayList<>();
		
		if(type.equalsIgnoreCase("nome")) {
			
			usuario = service.findAllByNome(value);
			
		}else if(type.equalsIgnoreCase("cpf")) {
			
			usuario = Arrays.asList(service.findByCPF(value));
			
		}else {
			throw new IllegalArgumentException("Dados inválidos!");
		}
		
		model.addObject("usuarios", usuario);
		return model;
	}
	
	@GetMapping(path = "pesquisar/cpf/{cpf}")
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView pesquisarPorCPF(@PathVariable String cpf) {
		ModelAndView model = new ModelAndView("home");
		
		Usuario usuario = service.findByCPF(cpf);
		
		model.addObject("usuarios", usuario);
		
		return model;
	}
	
	
	@GetMapping(path = "form")
	public ModelAndView editarUsuario(@RequestParam(required = false) Long id) {
		ModelAndView model = new ModelAndView("form");
		
		Usuario usuario = service.findById(id);
		
		model.addObject("estadoCivil", EstadoCivil.values());
		model.addObject("sexos", Sexo.values());
		
		model.addObject("usuario", usuario);
		
		return model;
	}
	
	@GetMapping(path = "delete")
	public ModelAndView deletar(@RequestParam Long id, RedirectAttributes redirect) {
		ModelAndView model = new ModelAndView("redirect:/");
		
		service.deleteById(id);
		
		redirect.addFlashAttribute("mensagem", "Usuário deletado com sucesso!");
		
		return model;
		
	}
	
	@GetMapping(path = "deleteAll")
	public ModelAndView deletarTodos(RedirectAttributes redirect) {
		ModelAndView model = new ModelAndView("redirect:/");
		
		service.deleteAll();
		
		redirect.addFlashAttribute("mensagem", "Todos os usuários foram deletados com sucesso!");
		
		return model;
	}
	
}
