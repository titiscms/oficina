package br.com.mjv.oficina.peca.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mjv.oficina.defeito.model.Defeito;
import br.com.mjv.oficina.defeito.service.DefeitoService;
import br.com.mjv.oficina.exception.BusinnessException;
import br.com.mjv.oficina.peca.model.Peca;
import br.com.mjv.oficina.peca.service.PecaService;

@Controller
@RequestMapping(path = "/peca")
public class PecaController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PecaController.class);
	
	private static final String MENSAGEM = "mensagem";
	
	private static final String REDIRECT_PECA_NOVO = "redirect:/peca/novo";
	
	@Autowired
	private PecaService pecaService;
	
	@Autowired
	private DefeitoService defeitoService;
	
	/**
	 * Método para iniciar a página de cadastro de peças e carregar os defeitos cadastrados no banco de dados.
	 * @return
	 */
	@GetMapping(path = "/novo")
	public ModelAndView cadastro() {
		
		List<Defeito> defeitos = defeitoService.listAll();
		
		ModelAndView mv = new ModelAndView("cadastroPeca");
		mv.addObject("defeitos", defeitos);
		
		return mv;
	}
	
	/**
	 * Método para retornar a página de item cadastrado com sucesso
	 * @param request
	 * @return envia para a página o recurso para servir de link para o botão "novo cadastro"
	 */
	@GetMapping(path = "/cadastrado")
	public ModelAndView cadastrado(HttpServletRequest request) {
		
		String[] uri = request.getRequestURI().split("/");
		
		ModelAndView mv = new ModelAndView("itemCadastrado");
		mv.addObject("recurso", uri[1]);
		
		return mv;
	}

	/**
	 * Método para cadastrar uma nova {@link Peca}
	 * @param atributos
	 * @param defeitosId
	 * @param nomePeca
	 * @return
	 */
	@PostMapping(path = "/cadastrar")
	public String cadastrar(RedirectAttributes atributos,
							@RequestParam(required = false) Integer[] defeitosId, 
							@RequestParam(name = "peca", required = false) String nomePeca) {

		LOGGER.info("Inicio do método cadastrar");
		
		if (defeitosId == null) {
			atributos.addFlashAttribute(MENSAGEM, "Selecione pelo menos um defeito.");
			return REDIRECT_PECA_NOVO;
		}
		
		if (StringUtils.isEmpty(nomePeca)) {
			atributos.addFlashAttribute(MENSAGEM, "Informe a peça.");
			return REDIRECT_PECA_NOVO;
		}
		
		try {
			pecaService.addDefeitoToPeca(defeitosId, nomePeca);
			
			LOGGER.info("Fim do método cadastrar");
			
			return "redirect:/peca/cadastrado";
			
		} catch (BusinnessException e) {
			LOGGER.error(e.getMessage(), e.getCause());
			atributos.addFlashAttribute(MENSAGEM, e.getMessage());
			return REDIRECT_PECA_NOVO;
		}
		
	}

}
