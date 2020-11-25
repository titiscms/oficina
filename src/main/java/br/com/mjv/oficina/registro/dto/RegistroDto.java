package br.com.mjv.oficina.registro.dto;

import java.util.Date;

import org.springframework.data.annotation.Id;

/**
 * Classe dto para transportar para a tela dados refrente a TB_REGISTRO e TB_VEICULO
 * @author thiago
 *
 */
public class RegistroDto {
	
	@Id
	private Integer id;
	
	private String cliente;
	
	private Date dataInclusao;
	
	private String veiculo;

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

	public String getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}
	
}
