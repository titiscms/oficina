package br.com.mjv.oficina.peca.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * Classe de mapeamento do modelo {@link Peca} para a tabela TB_PECA
 * @author thiago
 *
 */
public class PecaRowMapper implements RowMapper<Peca> {

	@Override
	public Peca mapRow(ResultSet rs, int rowNum) throws SQLException {
		Peca peca = new Peca();
		peca.setId(rs.getInt("id"));
		peca.setDescricao(rs.getString("descricao"));
		
		return peca;
	}

}
