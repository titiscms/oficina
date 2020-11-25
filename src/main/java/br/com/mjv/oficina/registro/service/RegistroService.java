package br.com.mjv.oficina.registro.service;

import java.util.List;

import br.com.mjv.oficina.registro.dto.RegistroDto;
import br.com.mjv.oficina.registro.filter.RegistroFilter;
import br.com.mjv.oficina.registro.model.Registro;

public interface RegistroService {

	/**
	 * Método para recuperar um lista de {@link RegistroDto} da base de dados
	 * @return
	 */
	List<RegistroDto> listAll();
		
	/**
	 * Método para salvar um novo {@link Registro} na base de dados
	 * @param registro
	 * @return
	 */
	Integer save(Registro registro);
	
	/**
	 * Método para recuperar um {@link RegistroDto} passando um filtro.
	 * @param filter
	 * @return
	 */
	List<RegistroDto> findRegistroByFilter(RegistroFilter filter);

	/**
	 * Método para incluir um novo {@link Registro} na base de dados;
	 * @param ItensId
	 * @param nomeCliente
	 * @param veiculoId
	 * @return
	 */
	void addRegistro(String[] ItensId, String nomeCliente, Integer veiculoId);

}
