package br.com.mjv.oficina;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/home")
public class TesteController {

	@GetMapping
	public String home() {
		return "home";
	}
	
	@GetMapping(path = "/pesquisa")
	public String pesquisa() {
		return "pesquisaVeiculos";
	}
	
	@GetMapping(path = "/registro")
	public String registro() {
		return "registroVeiculos";
	}
	
	@GetMapping(path = "/defeito-cadastrado")
	public String defeitoCadastrado() {
		return "defeitoCadastrado";
	}
	
	@GetMapping(path = "/cadastro-defeito")
	public String cadastroDefeito() {
		return "cadastroDefeito";
	}
	
	@GetMapping(path = "/cadastro-peca")
	public String cadastroPeca() {
		return "cadastroPeca";
	}
	
	@GetMapping(path = "/cadastro-veiculo")
	public String cadastroVeiculo() {
		return "cadastroVeiculo";
	}
	
	
}
