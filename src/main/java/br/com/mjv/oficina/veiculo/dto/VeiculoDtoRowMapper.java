package br.com.mjv.oficina.veiculo.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * Classe de mapeamento da classe {@link VeiculoDto} para tratar a associação 
 * entre as TB_VEICULO, TB_PECA, TB_DEFEITO
 * @author thiago
 *
 */
public class VeiculoDtoRowMapper implements RowMapper<VeiculoDto> {

	@Override
	public VeiculoDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		VeiculoDto veiculoDto = new VeiculoDto();
		veiculoDto.setPecaId(rs.getInt("pecaId"));
		veiculoDto.setPeca(rs.getString("peça"));
		veiculoDto.setDefeitoId(rs.getInt("defeitoId"));
		veiculoDto.setDefeito(rs.getString("defeito"));
		
		return veiculoDto;
	}

}
