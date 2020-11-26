package br.com.mjv.oficina.defeito.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mjv.oficina.defeito.dao.DefeitoDao;
import br.com.mjv.oficina.defeito.model.Defeito;
import br.com.mjv.oficina.exception.DefeitoJaCadastradoException;

@Service
public class DefeitoServiceImpl implements DefeitoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefeitoServiceImpl.class);

	@Autowired
	private DefeitoDao defeitoDao;
	
	@Override
	public List<Defeito> listAll() {
		return defeitoDao.listAll();
	}

	@Override
	@Transactional
	public Integer save(Defeito defeito) {
		LOGGER.info("Inicio do método save");
		
		Defeito defeitoCadastrado = defeitoDao.findByName(defeito.getDescricao());
		
		if(defeitoCadastrado != null) {
			LOGGER.error(String.format("Defeito %s já cadastrado", defeito.getDescricao()));
			throw new DefeitoJaCadastradoException(defeito.getDescricao());
		}
		
		LOGGER.info("Fim do método save");
		
		return defeitoDao.save(defeito);
	}

	@Override
	public Defeito findById(Integer defeitoId) {
		return defeitoDao.findById(defeitoId);
	}

}
