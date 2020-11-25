package br.com.mjv.oficina.veiculo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mjv.oficina.exception.BusinnessException;
import br.com.mjv.oficina.peca.model.Peca;
import br.com.mjv.oficina.peca.service.PecaService;
import br.com.mjv.oficina.veiculo.dto.VeiculoDto;
import br.com.mjv.oficina.veiculo.model.Veiculo;
import br.com.mjv.oficina.veiculo.service.VeiculoService;

@Controller
@RequestMapping(path = "/veiculo")
public class VeiculoController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VeiculoController.class);
	
	private static final String MENSAGEM = "mensagem";
	
	private static final String REDIRECT_VEICULO_NOVO = "redirect:/veiculo/novo";
	
	@Autowired
	private VeiculoService veiculoService;
	
	@Autowired
	private PecaService pecaService;
	
	/**
	 * Método para iniciar a página de cadastro de veículos e carregar as peças 
	 * @return
	 */
	@GetMapping(path = "/novo")
	public ModelAndView cadastro() {
		LOGGER.info("Inicio do método cadastro");
		
		List<Peca> pecas = pecaService.listAll();
		
		ModelAndView mv = new ModelAndView("cadastroVeiculo");
		mv.addObject("pecas", pecas);
		
		LOGGER.info("Fim do método cadastro");
		
		return mv;
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
		
		LOGGER.info("Inicio do método cadastrado");
		
		return mv;
	}
	
	/**
	 * Método para cadastrar um novo {@link Veiculo} na base de dados
	 * @param atributos
	 * @param pecasId
	 * @param nomeVeiculo
	 * @return
	 */
	@PostMapping(path = "/cadastrar")
	public String cadastrar(RedirectAttributes atributos,
			@RequestParam(required = false) Integer[] pecasId, 
			@RequestParam(name = "veiculo", required = false) String nomeVeiculo) {
		
		LOGGER.info("Inicio do método cadastrar");
		
		if (pecasId == null) {
			atributos.addFlashAttribute(MENSAGEM, "Selecione pelo menos uma peça.");
			return REDIRECT_VEICULO_NOVO;
		}
		
		if (StringUtils.isEmpty(nomeVeiculo)) {
			atributos.addFlashAttribute(MENSAGEM, "Informe o veiculo.");
			return REDIRECT_VEICULO_NOVO;
		}
		
		try {
			veiculoService.addPecaToVeiculo(pecasId, nomeVeiculo);
			
			LOGGER.info("Fim do método cadastrar");
			
			return "redirect:/veiculo/cadastrado";
			
		} catch (BusinnessException e) {
			LOGGER.error(e.getMessage(), e.getCause());
			atributos.addFlashAttribute(MENSAGEM, e.getMessage());
			return REDIRECT_VEICULO_NOVO;
		}
				
	}
	
	/**
	 * Método para buscar defeitos e peças de veiculo
	 * @param veiculoId
	 * @return
	 */
	@PostMapping(path = "/peca/defeito/buscar")
	public ResponseEntity<List<VeiculoDto>> buscarDefeitoEPecaDeVeiculo(@RequestBody Integer veiculoId) {
		LOGGER.info("Inicio do método buscarDefeitoEPecaDeVeiculo");
		
		List<VeiculoDto> veiculos = veiculoService.findPecaAndDefeitoFromVeiculoById(veiculoId);
				
		LOGGER.info("Fim do método buscarDefeitoEPecaDeVeiculo");
		
		return ResponseEntity.ok(veiculos);
	}

}
