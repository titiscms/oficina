package br.com.mjv.oficina.peca.service;

import java.util.List;

import br.com.mjv.oficina.peca.model.Peca;

public interface PecaService {

	/**
	 * Método para recuperar um lista de {@link Peca} da base de dados
	 * @return
	 */
	List<Peca> listAll();
	
	/**
	 * Método para salvar uma nova {@link Peca} na base de dados
	 * @param peca
	 * @return
	 */
	Integer save(Peca peca);

	/**
	 * Método para associar um array de ids de feitos à uma {@link Peca} 
	 * @param defeitosId
	 * @param nomePeca
	 */
	void addDefeitoToPeca(Integer[] defeitosId, String nomePeca);

}
