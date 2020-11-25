package br.com.mjv.oficina.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/home")
public class HomeController {
	
	/**
	 * Método para iniciar a página de cadastro de defeitos
	 * @return
	 */
	@GetMapping
	public String home() {
		return "home";
	}
	
}
