package br.com.mjv.oficina.veiculo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.mjv.oficina.exception.BusinnessException;
import br.com.mjv.oficina.veiculo.dto.VeiculoDto;
import br.com.mjv.oficina.veiculo.dto.VeiculoDtoRowMapper;
import br.com.mjv.oficina.veiculo.model.Veiculo;
import br.com.mjv.oficina.veiculo.model.VeiculoRowMapper;

@Repository
public class VeiculoDaoImpl implements VeiculoDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VeiculoDaoImpl.class);
	
	private static final String MESSAGE_ERROR_STANDARD = "Não foi possível concluir o cadastro.";
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@Autowired
	private DataSource dataSource;

	@Override
	public List<Veiculo> listAll() {
		MapSqlParameterSource params = new MapSqlParameterSource();
		StringBuilder sql = new StringBuilder("SELECT * FROM TB_VEICULO ORDER BY tipo ASC");
		
		return template.query(sql.toString(), params, new VeiculoRowMapper());
	}

	@Override
	@Transactional
	public Integer save(Veiculo veiculo) {
		try {
			LOGGER.info("Inicio do método save");
			
			SimpleJdbcInsert novoVeiculo = new SimpleJdbcInsert(dataSource).usingGeneratedKeyColumns("id");
			novoVeiculo.withTableName("TB_VEICULO");
			
			Map<String, Object> params = new HashMap<>();
			params.put("tipo", veiculo.getTipo());
			
			LOGGER.info("Fim do método save");
			
			return (Integer) novoVeiculo.executeAndReturnKey(params);
		} catch (Exception e) {
			LOGGER.error(String.format("Não foi possível incluir o veículo %s na base de dados", veiculo.getTipo()));
			throw new BusinnessException(MESSAGE_ERROR_STANDARD);
		}
	}
	
	@Override
	@Transactional
	public Integer addPecaToVeiculo(Integer pecaId, Integer veiculoId) {
		try {
			LOGGER.info("Inicio do método addPecaToVeiculo()");
			
			SimpleJdbcInsert novaPeca = new SimpleJdbcInsert(dataSource).usingGeneratedKeyColumns("id");
			novaPeca.withTableName("TB_PECA_VEICULO");
			
			Map<String, Object> params = new HashMap<>();
			params.put("id_peca", pecaId);
			params.put("id_veiculo", veiculoId);
			
			LOGGER.info("Fim do método addPecaToVeiculo");
			
			return (Integer) novaPeca.executeAndReturnKey(params);
		} catch (Exception e) {
			LOGGER.error(String.format("Não existe cadastro de peça de id %d ou veiculo de id %d.", pecaId, veiculoId));
			throw new BusinnessException(MESSAGE_ERROR_STANDARD);
		}
	}
	
	@Override
	public List<VeiculoDto> findPecaAndDefeitoFromVeiculo() {
		StringBuilder sql = new StringBuilder("SELECT P.ID pecaId, P.DESCRICAO peça, D.ID defeitoId, D.DESCRICAO defeito "
											+ "FROM TB_VEICULO V "
											+ "INNER JOIN TB_PECA_VEICULO PV ON PV.ID_VEICULO = V.ID "
											+ "INNER JOIN TB_PECA P ON P.ID = PV.ID_PECA "
											+ "INNER JOIN TB_DEFEITO_PECA DP ON DP.ID_PECA = P.ID "
											+ "INNER JOIN TB_DEFEITO D ON D.ID = DP.ID_DEFEITO");
				
		List<VeiculoDto> veiculosDto = template.query(sql.toString(), new MapSqlParameterSource(), new VeiculoDtoRowMapper());
		
		return veiculosDto;
	}

	@Override
	public List<VeiculoDto> findPecaAndDefeitoFromVeiculoById(Integer veiculoId) {

		LOGGER.info("Inicio do método findPecaAndDefeitoFromVeiculo(Integer veiculoId)");
		
		StringBuilder sql = new StringBuilder("SELECT P.ID pecaId, P.DESCRICAO peça, D.ID defeitoId, D.DESCRICAO defeito "
											+ "FROM TB_VEICULO V "
											+ "INNER JOIN TB_PECA_VEICULO PV ON PV.ID_VEICULO = V.ID "
											+ "INNER JOIN TB_PECA P ON P.ID = PV.ID_PECA "
											+ "INNER JOIN TB_DEFEITO_PECA DP ON DP.ID_PECA = P.ID "
											+ "INNER JOIN TB_DEFEITO D ON D.ID = DP.ID_DEFEITO "
											+ "WHERE v.id = :veiculoId");
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("veiculoId", veiculoId);
		
		List<VeiculoDto> veiculosDto = template.query(sql.toString(), params, new VeiculoDtoRowMapper());
		
		LOGGER.info("Inicio do método findPecaAndDefeitoFromVeiculo(Integer veiculoId)");
		
		return veiculosDto;
	}

	@Override
	public Veiculo findByName(String nomeVeiculo) {
		try {
			MapSqlParameterSource params = new MapSqlParameterSource();
			StringBuilder sql = new StringBuilder("SELECT * FROM TB_VEICULO WHERE UPPER(TIPO) = :nomeVeiculo");
			
			nomeVeiculo = nomeVeiculo.toUpperCase();
			params.addValue("nomeVeiculo", nomeVeiculo);
			
			Veiculo veiculoCadastrado = template.queryForObject(sql.toString(), params, new VeiculoRowMapper());
			
			return veiculoCadastrado;
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error(String.format("Não existe cadastro do veiculo %s.", nomeVeiculo));
			return null;
		}
	}

}
