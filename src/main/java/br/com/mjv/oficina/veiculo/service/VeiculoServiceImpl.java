package br.com.mjv.oficina.veiculo.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mjv.oficina.exception.VeiculoJaCadastradoException;
import br.com.mjv.oficina.veiculo.dao.VeiculoDao;
import br.com.mjv.oficina.veiculo.dto.VeiculoDto;
import br.com.mjv.oficina.veiculo.model.Veiculo;

@Service
public class VeiculoServiceImpl implements VeiculoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VeiculoServiceImpl.class);

	@Autowired
	private VeiculoDao veiculoDao;
	
	@Override
	public List<Veiculo> listAll() {
		return veiculoDao.listAll();
	}

	@Override
	@Transactional
	public Integer save(Veiculo veiculo) {
		return veiculoDao.save(veiculo);
	}
		
	@Override
	@Transactional
	public void addPecaToVeiculo(Integer[] pecasId, String nomeVeiculo) {
		LOGGER.info("Inicio do método addPecaToVeiculo()");
		
		Veiculo veiculoCadastrado = veiculoDao.findByName(nomeVeiculo);
		
		if (veiculoCadastrado == null) {
			Veiculo novoVeiculo = new Veiculo();
			novoVeiculo.setTipo(nomeVeiculo);
			
			Integer novoVeiculoId = save(novoVeiculo);
			
			List<Integer> pecasIdList = Arrays.asList(pecasId);
			pecasIdList.forEach(pecaId -> {
				veiculoDao.addPecaToVeiculo(pecaId, novoVeiculoId);
			});	
			
			LOGGER.info("Fim do método addPecaToVeiculo()");
		} else {
			LOGGER.error(String.format("Veiculo %s já cadastrado", nomeVeiculo));
			throw new VeiculoJaCadastradoException(nomeVeiculo);
		}
	}
	
	@Override
	public List<VeiculoDto> findPecaAndDefeitoFromVeiculo() {
		return veiculoDao.findPecaAndDefeitoFromVeiculo();
	}

	@Override
	public List<VeiculoDto> findPecaAndDefeitoFromVeiculoById(Integer veiculoId) {
		return veiculoDao.findPecaAndDefeitoFromVeiculoById(veiculoId);
	}
	
}
