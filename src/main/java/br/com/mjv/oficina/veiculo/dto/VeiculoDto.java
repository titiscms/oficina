package br.com.mjv.oficina.veiculo.dto;

/**
 * Classe DTO para tratar a associação entre as tabelas TB_VEICULO, TB_PECA, TB_DEFEITO 
 * @author thiago
 *
 */
public class VeiculoDto {
	
	private Integer pecaId;
	
	private String peca;
	
	private Integer defeitoId;
	
	private String defeito;

	public Integer getPecaId() {
		return pecaId;
	}

	public void setPecaId(Integer pecaId) {
		this.pecaId = pecaId;
	}

	public String getPeca() {
		return peca;
	}

	public void setPeca(String peca) {
		this.peca = peca;
	}

	public Integer getDefeitoId() {
		return defeitoId;
	}

	public void setDefeitoId(Integer defeitoId) {
		this.defeitoId = defeitoId;
	}

	public String getDefeito() {
		return defeito;
	}

	public void setDefeito(String defeito) {
		this.defeito = defeito;
	}
	
}
