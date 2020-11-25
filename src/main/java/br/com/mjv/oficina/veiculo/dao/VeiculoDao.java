package br.com.mjv.oficina.veiculo.dao;

import java.util.List;

import br.com.mjv.oficina.veiculo.dto.VeiculoDto;
import br.com.mjv.oficina.veiculo.model.Veiculo;

public interface VeiculoDao {

	/**
	 * Método para recuperar um lista de {@link Veiculo} da base de dados
	 * @return
	 */
	List<Veiculo> listAll();
	
	/**
	 * método pra incluir um novo {@link Veiculo} na base de dados
	 * @param veiculo
	 * @return
	 */
	Integer save(Veiculo veiculo);

	/**
	 * Método para associar um id de peça à um {@link Veiculo} na base de dados
	 * @param pecaId
	 * @param veiculoId
	 * @return
	 */
	Integer addPecaToVeiculo(Integer pecaId, Integer veiculoId);
	
	/**
	 * Método para buscar um lista {@link VeiculoDto} na base de dados.
	 * @return
	 */
	List<VeiculoDto> findPecaAndDefeitoFromVeiculo();

	/**
	 * Método para buscar um lista {@link VeiculoDto} na base de dados passando o id do veículo como referência.
	 * @param veiculoId
	 * @return
	 */
	List<VeiculoDto> findPecaAndDefeitoFromVeiculoById(Integer veiculoId);
	
	/**
	 * Método para buscar um {@link Veiculo} na base de dados passando o nome do veículo. 
	 * @param nomeVeiculo
	 * @return
	 */
	Veiculo findByName(String nomeVeiculo);
	
}
