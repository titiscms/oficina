package br.com.mjv.oficina.defeito.service;

import java.util.List;

import br.com.mjv.oficina.defeito.model.Defeito;

public interface DefeitoService {

	/**
	 * Método para recuperar um lista de {@link Defeito} da base de dados
	 * @return
	 */
	List<Defeito> listAll();
	
	/**
	 * Método para salvar um novo {@link Defeito} na base de dados
	 * @param defeito
	 * @return
	 */
	Integer save(Defeito defeito);
	
}
