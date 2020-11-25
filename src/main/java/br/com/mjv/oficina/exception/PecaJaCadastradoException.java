package br.com.mjv.oficina.exception;

/**
 * Exceção disparada quando tentamos cadastrar uma peça já cadastrada em nossa base de dados.
 * @author thiago
 *
 */
public class PecaJaCadastradoException extends BusinnessException {

	private static final long serialVersionUID = 1L;

	public PecaJaCadastradoException(String peca) {
		super(String.format("Peça %s já cadastrada na base de dados", peca));
	}

}