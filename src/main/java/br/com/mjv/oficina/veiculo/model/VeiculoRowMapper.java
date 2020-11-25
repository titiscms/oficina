package br.com.mjv.oficina.veiculo.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * Classe de mapeamento do modelo {@link Veiculo} para a tabela TB_VEICULO
 * @author thiago
 *
 */
public class VeiculoRowMapper implements RowMapper<Veiculo> {

	@Override
	public Veiculo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Veiculo veiculo = new Veiculo();
		veiculo.setId(rs.getInt("id"));
		veiculo.setTipo(rs.getString("tipo"));
		
		return veiculo;
	}

}
