package br.com.mjv.oficina.registro.dao;

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
import br.com.mjv.oficina.registro.dto.RegistroDto;
import br.com.mjv.oficina.registro.dto.RegistroDtoRowMapper;
import br.com.mjv.oficina.registro.filter.RegistroFilter;
import br.com.mjv.oficina.registro.model.Registro;
import br.com.mjv.oficina.registro.model.RegistroRowMapper;

@Repository
public class RegistroDaoImpl implements RegistroDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistroDaoImpl.class);
	
	private static final String MESSAGE_ERROR_STANDARD = "Não foi possível concluir o cadastro.";
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@Autowired
	private DataSource dataSource;

	@Override
	public List<RegistroDto> listAll() {
		MapSqlParameterSource params = new MapSqlParameterSource();
		StringBuilder sql = new StringBuilder("SELECT R.ID id, R.CLIENTE cliente, R.DATA_INCLUSAO data_inclusao, V.TIPO veiculo "
											+ "FROM TB_REGISTRO R "
											+ "INNER JOIN TB_VEICULO V ON V.ID = R.ID_VEICULO "
											+ "ORDER BY R.DATA_INCLUSAO DESC");
		
		return template.query(sql.toString(), params, new RegistroDtoRowMapper());
	}
	
	@Override
	public Registro findById(Integer id) {
		try {
			LOGGER.info("Inicio do método findById()");
			
			StringBuilder sql = new StringBuilder("SELECT * FROM TB_REGISTRO WHERE ID = :id ");
			
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("id", id);
			
			Registro registro = template.queryForObject(sql.toString(), params, new RegistroRowMapper());
			
			LOGGER.info("Fim do método findById()");
			
			return registro;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	@Transactional
	public Integer save(Registro registro) {
		try {
			LOGGER.info("Inicio do método save()");
			
			SimpleJdbcInsert novoRegistro = new SimpleJdbcInsert(dataSource).usingGeneratedKeyColumns("id");
			novoRegistro.withTableName("TB_REGISTRO");
			
			Map<String, Object> params = new HashMap<>();
			params.put("cliente", registro.getCliente());
			params.put("data_inclusao", registro.getDataInclusao());
			params.put("id_defeito", registro.getDefeitoId());
			params.put("id_peca", registro.getPecaId());
			params.put("id_veiculo", registro.getVeiculoId());
			
			LOGGER.info("Fim do método save()");
			
			return (Integer) novoRegistro.executeAndReturnKey(params);
		} catch (Exception e) {
			LOGGER.error(String.format("Não foi possível incluir o registro do cliente %s de id %d "
					+ "na base de dados", registro.getCliente(), registro.getId()));
			throw new BusinnessException(MESSAGE_ERROR_STANDARD);
		}
	}

	@Override
	public List<RegistroDto> findRegistroByFilter(RegistroFilter filter) {
		LOGGER.info("Inicio do método findRegistroByFilter()");

		StringBuilder sql = new StringBuilder("SELECT R.ID id, R.CLIENTE cliente, R.DATA_INCLUSAO data_inclusao, V.TIPO veiculo "
											+ "FROM TB_REGISTRO R "
											+ "INNER JOIN TB_VEICULO V ON V.ID = R.ID_VEICULO "
											+ "WHERE 1 = 1 ");
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		String timeInicio = "00:00:00";
		String timeFim = "23:59:59";
		
		if (filter.getVeiculoId() != null) {
			sql.append("AND V.ID = :veiculoId ");
			params.addValue("veiculoId", filter.getVeiculoId());
		}
		
		if (!filter.getDataInicio().isEmpty()) {
			sql.append("AND R.DATA_INCLUSAO >= :dataInicio ");
			params.addValue("dataInicio", filter.getDataInicio() + " " + timeInicio);
		}
		
		if (!filter.getDataFim().isEmpty()) {
			sql.append("AND R.DATA_INCLUSAO <= :dataFim ");
			params.addValue("dataFim", filter.getDataFim() + " " + timeFim);
		}
		
		sql.append("ORDER BY R.DATA_INCLUSAO DESC");
		
		List<RegistroDto> registrosDto = template.query(sql.toString(), params, new RegistroDtoRowMapper());
		
		LOGGER.info("Inicio do método findRegistroByFilter()");
		
		return registrosDto;
	}

}
