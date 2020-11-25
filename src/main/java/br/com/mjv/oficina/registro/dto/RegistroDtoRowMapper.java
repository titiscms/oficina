package br.com.mjv.oficina.registro.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * Classe de mapeamento da classe dto {@link RegistroDto} para tratar associação TB_REGISTRO e TB_VEICULO
 * @author thiago
 *
 */
public class RegistroDtoRowMapper implements RowMapper<RegistroDto> {

	@Override
	public RegistroDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		RegistroDto registroDto = new RegistroDto();
		registroDto.setId(rs.getInt("id"));
		registroDto.setCliente(rs.getString("cliente"));
		registroDto.setDataInclusao(rs.getDate("data_inclusao"));
		registroDto.setVeiculo(rs.getString("veiculo"));
		
		return registroDto;
	}

}
