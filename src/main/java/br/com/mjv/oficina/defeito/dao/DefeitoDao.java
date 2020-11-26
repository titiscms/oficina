package br.com.mjv.oficina.defeito.dao;

import java.util.List;

import br.com.mjv.oficina.defeito.model.Defeito;

public interface DefeitoDao {

	/**
	 * Método para recuperar um lista de {@link Defeito} da base de dados
	 * @return
	 */
	List<Defeito> listAll();
	
	/**
	 * método pra incluir um novo {@link Defeito} na base de dados
	 * @param defeito
	 * @return
	 */
	Integer save(Defeito defeito);
	
	/**
	 * Método para buscar um {@link Defeito} na base de dados passando o nome do defeito. 
	 * @param nomeDefeito
	 * @return
	 */
	Defeito findByName(String nomeDefeito);

	/**
	 * Método para recuperar um {@link Defeito} da base de dados passando um id de defeito
	 * @param defeitoId
	 * @return
	 */
	Defeito findById(Integer defeitoId);
	
}
