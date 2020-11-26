package br.com.mjv.oficina.registro.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mjv.oficina.defeito.model.Defeito;
import br.com.mjv.oficina.defeito.service.DefeitoService;
import br.com.mjv.oficina.exception.BusinnessException;
import br.com.mjv.oficina.peca.model.Peca;
import br.com.mjv.oficina.peca.service.PecaService;
import br.com.mjv.oficina.registro.dto.RegistroDto;
import br.com.mjv.oficina.registro.filter.RegistroFilter;
import br.com.mjv.oficina.registro.model.Registro;
import br.com.mjv.oficina.registro.service.RegistroService;
import br.com.mjv.oficina.veiculo.dto.VeiculoDto;
import br.com.mjv.oficina.veiculo.model.Veiculo;
import br.com.mjv.oficina.veiculo.service.VeiculoService;

@Controller
@RequestMapping(path = "/registro")
public class RegistroController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistroController.class);
	
	private static final String MENSAGEM = "mensagem";
	
	private static final String REDIRECT_REGISTRO_NOVO = "redirect:/registro/novo";
	
	@Autowired
	private RegistroService registroService;
	
	@Autowired
	private VeiculoService veiculoService;
	
	@Autowired
	private PecaService pecaService;
	
	@Autowired
	private DefeitoService defeitoService;
	
	/**
	 * Método para iniciar a página de registros de defeitos de veiculos, carregando os veiculos cadastrados na base e o
	 * @return
	 */
	@GetMapping(path = "/novo")	
	public ModelAndView cadastro() {
		
		LOGGER.info("Inicio do método cadastro()");
		
		List<Veiculo> veiculos = veiculoService.listAll();
		List<VeiculoDto> listPecaDefeito = veiculoService.findPecaAndDefeitoFromVeiculo();
		Date acesso = new Date();
		
		ModelAndView mv = new ModelAndView("registroVeiculos");
		mv.addObject("veiculos", veiculos);
		mv.addObject("listPecaDefeito", listPecaDefeito);
		mv.addObject("acesso", acesso);
		
		LOGGER.info("Fim do método cadastro()");
		
		return mv;
	}
	
	/**
	 * Método para retornar a página de item cadastrado com sucesso
	 * @param request
	 * @return envia para a página o recurso para servir de link para o botão "novo cadastro"
	 */
	@GetMapping(path = "/cadastrado")
	public ModelAndView cadastrado(HttpServletRequest request) {
		
		LOGGER.info("Inicio do método cadastrado()");
		
		String[] uri = request.getRequestURI().split("/");
		
		ModelAndView mv = new ModelAndView("itemCadastrado");
		mv.addObject("recurso", uri[1]);
		
		LOGGER.info("Fim do método cadastrado()");
		
		return mv;
	}
	
	/**
	 * Método para cadastrar um novo {@link Registro}
	 * @param registro
	 * @return
	 */
	@PostMapping(path = "/cadastrar")	
	public String cadastrar(RedirectAttributes atributos,
			@RequestParam(required = false) String[] idsItem, 
			@RequestParam(required = false) String cliente, 
			@RequestParam(name = "tipoVeiculo", required = false) Integer veiculoId) {
		
		LOGGER.info("Inicio do método cadastrar()");
		
		
		if (idsItem == null) {
			atributos.addFlashAttribute(MENSAGEM, "Selecione item para cadastrar um registro.");
			return REDIRECT_REGISTRO_NOVO;
		}
		
		if (StringUtils.isEmpty(cliente)) {
			atributos.addFlashAttribute(MENSAGEM, "Informe um cliente.");
			return REDIRECT_REGISTRO_NOVO;
		}
		
		if (veiculoId == null) {
			atributos.addFlashAttribute(MENSAGEM, "Informe o tipo de veiculo.");
			return REDIRECT_REGISTRO_NOVO;
		}
		
		try {
			registroService.addRegistro(idsItem, cliente, veiculoId);
			
			LOGGER.info("Fim do método cadastrar()");
			
			return "redirect:/registro/cadastrado";
		} catch (BusinnessException e) {
			LOGGER.error(e.getMessage(), e);
			atributos.addFlashAttribute(MENSAGEM, "Ocorreu um erro ao cadastrar um registro.");
			return REDIRECT_REGISTRO_NOVO;
		}

	}
	
	/**
	 * Método para iniciar a página de pesquisa de registros
	 * @return
	 */
	@GetMapping
	public ModelAndView pesquisa() {
		
		LOGGER.info("Inicio do método pesquisa()");
		
		List<Veiculo> veiculos = veiculoService.listAll();
		List<RegistroDto> registros = registroService.listAll();
		
		ModelAndView mv = new ModelAndView("pesquisaVeiculos");
		mv.addObject("registros", registros);
		mv.addObject("veiculos", veiculos);
		
		LOGGER.info("Fim do método pesquisa()");
		
		return mv;
	}
	
	@PostMapping(path = "/buscar")
	public ResponseEntity<List<RegistroDto>> buscarRegistroPorFiltro(@RequestBody RegistroFilter filter) {
		LOGGER.info("Inicio do método buscarRegistroPorFiltro()");
		
		List<RegistroDto> registros = registroService.findRegistroByFilter(filter);
		
		LOGGER.info("Fim do método buscarRegistroPorFiltro()");
		
		return ResponseEntity.ok(registros);
	}
	
	@GetMapping(path = "/{registroId}")
	public ModelAndView buscar(@PathVariable Integer registroId) {
		
		LOGGER.info("Inicio do método buscar()");
		
		Registro registro = registroService.findById(registroId);
		Veiculo veiculo = veiculoService.findById(registro.getVeiculoId());
		Peca peca = pecaService.findById(registro.getPecaId());
		Defeito defeito = defeitoService.findById(registro.getDefeitoId());
		
		Date dataAlteracao = new Date();
		
		ModelAndView mv = new ModelAndView("registro");
		mv.addObject("registro", registro);
		mv.addObject("veiculo", veiculo);
		mv.addObject("peca", peca);
		mv.addObject("defeito", defeito);
		
		mv.addObject("dataAlteracao", dataAlteracao);
		
		LOGGER.info("Fim do método buscar()");
		
		return mv;
	}
	
}
