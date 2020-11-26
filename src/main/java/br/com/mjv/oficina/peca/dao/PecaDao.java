package br.com.mjv.oficina.peca.dao;

import java.util.List;

import br.com.mjv.oficina.peca.model.Peca;

public interface PecaDao {

	/**
	 * Método para recuperar uma lista de {@link Peca} da base de dados
	 * @return
	 */
	List<Peca> listAll();
	
	/**
	 * método pra incluir uma nova {@link Peca} na base de dados
	 * @param peca
	 * @return
	 */
	Integer save(Peca peca);
	
	/**
	 * Método para associar um id de defeito à uma {@link Peca} 
	 * @param defeitoId
	 * @param pecaId
	 * @return
	 */
	Integer addDefeitoToPeca(Integer defeitoId, Integer pecaId);
	
	/**
	 * Método para buscar uma {@link Peca} na base de dados passando o nome da peça. 
	 * @param nomePeca
	 * @return
	 */
	Peca findByName(String nomePeca);

	/**
	 * Método para recuperar uma {@link Peca} da base de dados passando o id da peça
	 * @param pecaId
	 * @return
	 */
	Peca findById(Integer pecaId);
	
}
