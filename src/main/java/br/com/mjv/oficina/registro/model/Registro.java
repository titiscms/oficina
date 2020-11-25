package br.com.mjv.oficina.registro.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

/**
 * Classe de modelo referente a tabela TB_REGISTRO 
 * @author thiago
 *
 */
public class Registro {
	
	@Id
	private Integer id;
	
	private String cliente;
	
	private Date dataInclusao;
	
	private Integer defeitoId;
	
	private Integer pecaId;
	
	private Integer veiculoId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Integer getDefeitoId() {
		return defeitoId;
	}

	public void setDefeitoId(Integer defeitoId) {
		this.defeitoId = defeitoId;
	}

	public Integer getPecaId() {
		return pecaId;
	}

	public void setPecaId(Integer pecaId) {
		this.pecaId = pecaId;
	}

	public Integer getVeiculoId() {
		return veiculoId;
	}

	public void setVeiculoId(Integer veiculoId) {
		this.veiculoId = veiculoId;
	}
	
}
