package br.com.mjv.oficina.peca.dao;

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
import br.com.mjv.oficina.peca.model.Peca;
import br.com.mjv.oficina.peca.model.PecaRowMapper;

@Repository
public class PecaDaoImpl implements PecaDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PecaDaoImpl.class);
	
	private static final String MESSAGE_ERROR_STANDARD = "Não foi possível concluir o cadastro.";
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@Autowired
	private DataSource dataSource;

	@Override
	public List<Peca> listAll() {
		MapSqlParameterSource params = new MapSqlParameterSource();
		StringBuilder sql = new StringBuilder("SELECT * FROM TB_PECA ORDER BY DESCRICAO ASC");
		
		return template.query(sql.toString(), params, new PecaRowMapper());
	}

	@Override
	@Transactional
	public Integer save(Peca peca) {
		try {
			LOGGER.info("Inicio do método save");
			
			SimpleJdbcInsert novaPeca = new SimpleJdbcInsert(dataSource).usingGeneratedKeyColumns("id");
			novaPeca.withTableName("TB_PECA");
			
			Map<String, Object> params = new HashMap<>();
			params.put("descricao", peca.getDescricao());
			
			LOGGER.info("Fim do método save");
			
			return (Integer) novaPeca.executeAndReturnKey(params);
			
		} catch (Exception e) {
			LOGGER.error(String.format("Não foi possível incluir a peca %s na base de dados", peca.getDescricao()));
			throw new BusinnessException(MESSAGE_ERROR_STANDARD);
		}
	}

	@Override
	@Transactional
	public Integer addDefeitoToPeca(Integer defeitoId, Integer pecaId) {
		try {
			LOGGER.info("Inicio do método addDefeitoToPeca");
			
			SimpleJdbcInsert novaPeca = new SimpleJdbcInsert(dataSource).usingGeneratedKeyColumns("id");
			novaPeca.withTableName("TB_DEFEITO_PECA");
			
			Map<String, Object> params = new HashMap<>();
			params.put("id_defeito", defeitoId);
			params.put("id_peca", pecaId);
			
			LOGGER.info("Inicio do método addDefeitoToPeca");
			
			return (Integer) novaPeca.executeAndReturnKey(params);
		} catch (Exception e) {
			LOGGER.error(String.format("Não existe cadastro de defeito de id %d ou peça de id %d.", defeitoId, pecaId));
			throw new BusinnessException(MESSAGE_ERROR_STANDARD);
		}
	}
	
	@Override
	public Peca findByName(String nomePeca) {
		try {
			MapSqlParameterSource params = new MapSqlParameterSource();
			StringBuilder sql = new StringBuilder("SELECT * FROM TB_PECA WHERE UPPER(DESCRICAO) = :nomePeca");
			
			nomePeca = nomePeca.toUpperCase();
			params.addValue("nomePeca", nomePeca);
			
			Peca pecaCadastrada = template.queryForObject(sql.toString(), params, new PecaRowMapper());
			
			return pecaCadastrada;
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error(String.format("Não existe cadastro da peça %s.", nomePeca));
			return null;
		}
	}	
}
