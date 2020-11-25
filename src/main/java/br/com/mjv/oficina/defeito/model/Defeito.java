package br.com.mjv.oficina.defeito.model;

import org.springframework.data.annotation.Id;

/**
 * Classe de modelo referente a tabela TB_DEFEITO 
 * @author thiago
 *
 */
public class Defeito {
	
	@Id
	private Integer id;
	
	private String descricao;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
