package br.com.mjv.oficina.peca.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mjv.oficina.exception.PecaJaCadastradoException;
import br.com.mjv.oficina.peca.dao.PecaDao;
import br.com.mjv.oficina.peca.model.Peca;

@Service
public class PecaServiceImpl implements PecaService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PecaServiceImpl.class);

	@Autowired
	private PecaDao pecaDao;
	
	@Override
	public List<Peca> listAll() {
		return pecaDao.listAll();
	}

	@Override
	@Transactional
	public Integer save(Peca peca) {
		return pecaDao.save(peca);
	}

	@Override
	@Transactional
	public void addDefeitoToPeca(Integer[] defeitosId, String nomePeca) {
		
		LOGGER.info("Inicio do método addDefeitoToPeca");
		
		Peca pecaCadastrada = pecaDao.findByName(nomePeca);
		
		if (pecaCadastrada == null) {
			Peca novaPeca = new Peca();
			novaPeca.setDescricao(nomePeca);
			
			Integer novaPecaId = save(novaPeca);
			
			List<Integer> defeitosIdList = Arrays.asList(defeitosId);
			defeitosIdList.forEach(defeitoId -> {
				pecaDao.addDefeitoToPeca(defeitoId, novaPecaId);
			});	
			
			LOGGER.info("Fim do método addDefeitoToPeca");
			
		} else {
			LOGGER.error(String.format("Peça %s já cadastrada", nomePeca));
			throw new PecaJaCadastradoException(nomePeca);
		}
		
	}

	@Override
	public Peca findById(Integer pecaId) {
		return pecaDao.findById(pecaId);
	}

}
