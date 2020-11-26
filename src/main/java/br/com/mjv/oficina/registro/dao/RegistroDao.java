package br.com.mjv.oficina.registro.dao;

import java.util.List;

import br.com.mjv.oficina.registro.dto.RegistroDto;
import br.com.mjv.oficina.registro.filter.RegistroFilter;
import br.com.mjv.oficina.registro.model.Registro;

public interface RegistroDao {

	/**
	 * Método para recuperar um lista de {@link Registro} da base de dados
	 * @return
	 */
	List<RegistroDto> listAll();
	
	/**
	 * Método pra buscar um {@link Registro} da base de dados passando o id do registro como referência.
	 * @param id
	 * @return
	 */
	Registro findById(Integer id);
	
	/**
	 * Método pra incluir um novo {@link Registro} na base de dados
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

}
