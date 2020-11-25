package br.com.mjv.oficina.veiculo.model;

import org.springframework.data.annotation.Id;

/**
 * Classe de modelo referente a tabela TB_VEICULO 
 * @author thiago
 *
 */
public class Veiculo {
	
	@Id
	private Integer id;
	
	private String tipo;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
