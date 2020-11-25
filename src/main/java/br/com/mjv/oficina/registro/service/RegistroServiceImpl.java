package br.com.mjv.oficina.registro.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mjv.oficina.exception.DataInvalidaException;
import br.com.mjv.oficina.registro.dao.RegistroDao;
import br.com.mjv.oficina.registro.dto.RegistroDto;
import br.com.mjv.oficina.registro.filter.RegistroFilter;
import br.com.mjv.oficina.registro.model.Registro;

@Service
public class RegistroServiceImpl implements RegistroService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistroServiceImpl.class);

	@Autowired
	private RegistroDao registroDao;
	
	@Override
	public List<RegistroDto> listAll() {
		List<RegistroDto> registros = registroDao.listAll();
		
		return registros;
	}
	
	@Override
	public List<RegistroDto> findRegistroByFilter(RegistroFilter filter) {
		try {
			LOGGER.info("Inicio do método findRegistroByFilter()");
			
			if (!filter.getDataInicio().isEmpty()) {
				String dataInicio = formatDateToSearch(filter.getDataInicio());
				filter.setDataInicio(dataInicio);
			}
			
			if (!filter.getDataFim().isEmpty()) {
				String dataFim = formatDateToSearch(filter.getDataFim());
				filter.setDataFim(dataFim);				
			}
			
			LOGGER.info("Fim do método findRegistroByFilter()");
			
			return registroDao.findRegistroByFilter(filter);
		} catch (ParseException e) {
			LOGGER.error("Erro na conversação da data a ser pesquisada");
			throw new DataInvalidaException(filter.getDataInicio(), filter.getDataFim());
		}

	}

	@Override
	@Transactional
	public Integer save(Registro registro) {
		return registroDao.save(registro);
	}
	
	@Override
	@Transactional
	public void addRegistro(String[] itensId, String nomeCliente, Integer veiculoId) {
		LOGGER.info("Inicio do método addRegistro()");
		
		List<String> idsItemList = Arrays.asList(itensId);
		idsItemList.forEach(idItem -> {
			String[] id = idItem.split("_");
			
			Integer defeitoId = Integer.valueOf(id[0]);
			Integer pecaId = Integer.valueOf(id[1]);
			
			Registro novoRegistro = new Registro();
			novoRegistro.setCliente(nomeCliente);
			novoRegistro.setDataInclusao(new Date());
			novoRegistro.setDefeitoId(defeitoId);
			novoRegistro.setPecaId(pecaId);
			novoRegistro.setVeiculoId(veiculoId);
			
			registroDao.save(novoRegistro );
		});
		
		LOGGER.info("Fim do método addRegistro()");
	}
	
	private String formatDateToSearch(String data) throws ParseException {
		
		SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
		
		Date dataInicioDate = input.parse(data);
		String dataInicioFormatada = output.format(dataInicioDate);
		
		return dataInicioFormatada;
	}
	
}
