package br.com.mjv.oficina.defeito.dao;

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

import br.com.mjv.oficina.defeito.model.Defeito;
import br.com.mjv.oficina.defeito.model.DefeitoRowMapper;
import br.com.mjv.oficina.exception.BusinnessException;

@Repository
public class DefeitoDaoImpl implements DefeitoDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefeitoDaoImpl.class);
	
	private static final String MESSAGE_ERROR_STANDARD = "Não foi possível concluir o cadastro.";
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@Autowired
	private DataSource dataSource;

	@Override
	public List<Defeito> listAll() {
		MapSqlParameterSource params = new MapSqlParameterSource();
		StringBuilder sql = new StringBuilder("SELECT * FROM TB_DEFEITO ORDER BY DESCRICAO ASC");
		
		return template.query(sql.toString(), params, new DefeitoRowMapper());
	}

	@Override
	@Transactional
	public Integer save(Defeito defeito) {
		try {
			LOGGER.info("Inicio do método save");
			
			SimpleJdbcInsert novoDefeito = new SimpleJdbcInsert(dataSource).usingGeneratedKeyColumns("id");
			novoDefeito.withTableName("TB_DEFEITO");
			
			Map<String, Object> params = new HashMap<>();
			params.put("descricao", defeito.getDescricao());
			
			LOGGER.info("Fim do método save");
			
			return (Integer) novoDefeito.executeAndReturnKey(params);
		} catch (Exception e) {
			LOGGER.error(String.format("Não foi possível incluir o defeito %s na base de dados", defeito.getDescricao()));
			throw new BusinnessException(MESSAGE_ERROR_STANDARD);
		}
	}
	
	@Override
	public Defeito findByName(String nomeDefeito) {
		try {
			MapSqlParameterSource params = new MapSqlParameterSource();
			StringBuilder sql = new StringBuilder("SELECT * FROM TB_DEFEITO WHERE UPPER(DESCRICAO) = :defeito");
			
			nomeDefeito = nomeDefeito.toUpperCase();
			params.addValue("defeito", nomeDefeito);
			
			Defeito defeitoCadastrado = template.queryForObject(sql.toString(), params, new DefeitoRowMapper());
			
			return defeitoCadastrado;
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error(String.format("Defeito %s não encontrado", nomeDefeito));
			return null;
		}
	}

}
