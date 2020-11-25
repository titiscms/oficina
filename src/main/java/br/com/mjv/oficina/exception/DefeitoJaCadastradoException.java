package br.com.mjv.oficina.exception;

/**
 * Exceção disparada quando tentamos cadastrar um defeito já cadastrado em nossa base de dados.
 * @author thiago
 *
 */
public class DefeitoJaCadastradoException extends BusinnessException {

	private static final long serialVersionUID = 1L;

	public DefeitoJaCadastradoException(String defeito) {
		super(String.format("Defeito %s já cadastrado na base de dados", defeito));
	}

}
