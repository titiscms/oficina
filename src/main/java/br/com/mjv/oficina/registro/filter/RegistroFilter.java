package br.com.mjv.oficina.registro.filter;

/**
 * Classe que representa os filtros a ser pesquisados na TB_REGISTRO
 * @author thiago
 *
 */
public class RegistroFilter {
	
	private Integer veiculoId;
	
	private String dataInicio;

	private String dataFim;

	public Integer getVeiculoId() {
		return veiculoId;
	}

	public void setVeiculoId(Integer veiculoId) {
		this.veiculoId = veiculoId;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

}
