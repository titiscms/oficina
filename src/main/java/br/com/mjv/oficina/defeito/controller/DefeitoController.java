package br.com.mjv.oficina.defeito.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mjv.oficina.defeito.model.Defeito;
import br.com.mjv.oficina.defeito.service.DefeitoService;
import br.com.mjv.oficina.exception.BusinnessException;

@Controller
@RequestMapping(path = "/defeito")
public class DefeitoController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefeitoController.class);
	
	private static final String MENSAGEM = "mensagem";
	
	@Autowired
	private DefeitoService defeitoService;
	
	/**
	 * Método para iniciar a página de cadastro de defeitos
	 * @return
	 */
	@GetMapping(path = "/novo")
	public String cadastro() {
		LOGGER.info("Inicio do método cadastro");
		
		LOGGER.info("Fim do método cadastro");
		
		return "cadastroDefeito";
	}
	
	/**
	 * Método para retornar a página de item cadastrado com sucesso
	 * @param request
	 * @return envia para a página o recurso para servir de link para o botão "novo cadastro"
	 */
	@GetMapping(path = "/cadastrado")
	public ModelAndView cadastrado(HttpServletRequest request) {
		LOGGER.info("Inicio do método cadastrado");
		
		String[] uri = request.getRequestURI().split("/");
		
		ModelAndView mv = new ModelAndView("itemCadastrado");
		mv.addObject("recurso", uri[1]);
		
		LOGGER.info("Fim do método cadastrado");
		
		return mv;
	}
	
	/**
	 * Método para cadastrar um novo {@link Defeito}
	 * @param atributos
	 * @param defeito
	 * @return
	 */
	@PostMapping(path = "/cadastrar")
	public String cadastrar(RedirectAttributes atributos, Defeito defeito) {
		try {
			LOGGER.info("Inicio do método cadastrar");
			
			defeitoService.save(defeito);
			
			LOGGER.info("Fim do método cadastrar");
			
			return "redirect:/defeito/cadastrado";
		} catch (BusinnessException e) {
			LOGGER.error(String.format(e.getMessage()));
			
			atributos.addFlashAttribute(MENSAGEM, e.getMessage());
			
			return "redirect:/defeito/novo";
		}
	}
	
}
