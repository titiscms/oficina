package br.com.mjv.oficina.registro.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * Classe de mapeamento do modelo {@link Registro} para a tabela TB_REGISTRO
 * @author thiago
 *
 */
public class RegistroRowMapper implements RowMapper<Registro> {

	@Override
	public Registro mapRow(ResultSet rs, int rowNum) throws SQLException {
		Registro registro = new Registro();
		registro.setId(rs.getInt("id"));
		registro.setCliente(rs.getString("cliente"));
		registro.setDataInclusao(rs.getDate("data_inclusao"));
		registro.setDefeitoId(rs.getInt("id_defeito"));
		registro.setPecaId(rs.getInt("id_peca"));
		registro.setVeiculoId(rs.getInt("id_veiculo"));
		
		return registro;
	}

}
